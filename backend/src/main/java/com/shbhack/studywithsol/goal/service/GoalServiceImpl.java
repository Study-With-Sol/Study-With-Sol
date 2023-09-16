package com.shbhack.studywithsol.goal.service;

import com.shbhack.studywithsol.goal.domain.Goal;
import com.shbhack.studywithsol.goal.dto.GoalDto;
import com.shbhack.studywithsol.goal.repository.GoalRepository;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService{
    private final GoalRepository goalRepository;
    private final TransactionService transactionService;
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    @Override
    public String registerLongGoal(Long parentId, GoalDto.GoalReqDto goalReqDto) {
        Goal goal = Goal.of(parentId, goalReqDto);
        goalRepository.save(goal);
        return "register success";
    }

    @Override
    public String deleteLongGoal(Long goalId) {
        Goal goal = goalRepository.findByGoalIdAndPayIsFalse(goalId)
                .orElseThrow(()->new BusinessException(ErrorMessage.HAVE_NOT_LONG_GOAL));
        // 모여진 돈 children 계좌로 바로 이체
        User parent = userRepository.findById(goal.getParentId())
                .orElseThrow(()-> new BusinessException(ErrorMessage.PARENT_NOT_FOUND));
        User children = userRepository.findById(goal.getChildrenId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Long connectionId = connectionRepository.findByParentAndChildren(parent, children).getConnectionId();
        transactionService.payStudyMoney(connectionId, goal.getContent(), goal.getMoney());
        goal.updatePay();
        return "delete success";
    }

    @Override
    public List<GoalDto.GoalRespDto> getLongGoal(Long childrenId) {
        List<GoalDto.GoalRespDto> list = new ArrayList<>();
        List<Goal> goals = goalRepository.findAllByChildrenId(childrenId);
        for(Goal goal: goals){
            if(!goal.getPay())
                list.add(GoalDto.GoalRespDto.from(goal));
        }
        return list;
    }

    @Override
    public GoalDto.GoalRespDto getLongGoalParent(Long parentId, Long childrenId) {
        Goal goal = goalRepository.findByParentIdAndChildrenIdAndPayIsFalse(parentId, childrenId)
                .orElseThrow(()->new BusinessException(ErrorMessage.HAVE_NOT_LONG_GOAL));
        return GoalDto.GoalRespDto.from(goal);
    }
}
