package com.shbhack.studywithsol.timer.service;

import com.shbhack.studywithsol.goal.domain.Goal;
import com.shbhack.studywithsol.goal.repository.GoalRepository;
import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.timer.repository.TimerRepository;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService {
    private final TimerRepository timerRepository;
    private final GoalRepository goalRepository;
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    @Override
    public String studyWithTimer(Long childrenId, TimerDto.StudyWithTimerReqDto studyWithTimerReqDto) {
        Timer timer = Timer.of(childrenId, studyWithTimerReqDto);
        timerRepository.save(timer);
        return "request success";
    }

    @Override
    public List<TimerDto.ListResDto> getListTimer(Long parentId, Long childrenId) {
        List<Timer> timers = timerRepository.findAllByParentIdAndChildrenIdAndPayStateIsFalse(parentId, childrenId);
        List<TimerDto.ListResDto> timerList = new ArrayList<>();
        for(Timer timer : timers){
            timerList.add(TimerDto.ListResDto.from(timer));
        }
        return timerList;
    }

    @Override
    public TimerDto.ResponseDto paidMoneyAboutTimer(Long studyAmountId, TimerDto.ParentReqDto parentReqDto) {
        Long money = parentReqDto.getMoney();
        Timer timer = timerRepository.findById(studyAmountId)
                .orElseThrow(()->new BusinessException(ErrorMessage.STUDY_NOT_FOUNT));
        timer.paidMoney(money);

        User parent = userRepository.findById(timer.getParentId())
                .orElseThrow(()->new BusinessException(ErrorMessage.PARENT_NOT_FOUND));
        User children = userRepository.findById(timer.getChildrenId())
                .orElseThrow(()->new BusinessException(ErrorMessage.CHILD_NOT_FOUND));
        Connection connection = connectionRepository.findByParentAndChildren(parent, children);
        if(timer.getWantPay()== WantPay.KEEP){ // 적금으로
            Goal goal = goalRepository.findByParentIdAndChildrenIdAndPayIsFalse(
                    timer.getParentId(), timer.getChildrenId()
            ). orElseThrow(()->new BusinessException(ErrorMessage.HAVE_NOT_LONG_GOAL));
            goal.updateMoney(money);
            // 부모 계좌에서 돈 빠져나감
            Long accountId = parent.getMainAccount().getId();
            TransactionCreateRequest request = new TransactionCreateRequest(
                    accountId, timer.getTime(), timer.getMoney(), false, children.getName(), parent.getName()
            );
            transactionService.save(request);
        } else{ // 용돈으로
            // 부모가 자녀에게 바로 이체
            transactionService.payStudyMoney(
                    connection.getConnectionId(), timer.getContent(), money
            );
        }
        return TimerDto.ResponseDto.of(money, timer.getPayState());
    }

}
