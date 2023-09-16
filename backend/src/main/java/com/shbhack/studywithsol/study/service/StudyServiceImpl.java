package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.goal.domain.Goal;
import com.shbhack.studywithsol.goal.repository.GoalRepository;
import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.domain.StudyState;
import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.repository.StudyRepository;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService{
    private final StudyRepository studyRepository;
    private final GoalRepository goalRepository;
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    @Override
    public String registerStudyList(Long parentId, StudyDto.RegisterStudyListReqDto registerStudyListReqDto) {
        Study study = Study.of(parentId, registerStudyListReqDto);
        studyRepository.save(study);
        return "register success";
    }

    @Override
    public void deleteOne(Long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);
        if(study.isEmpty()){
            throw new BusinessException(ErrorMessage.STUDY_NOT_FOUNT);
        }
        if(study.get().getIsDone()){
            throw new BusinessException(ErrorMessage.STUDY_ALREADY_DONE);
        }
        String content = study.get().getContent();
        studyRepository.deleteById(studyId);
        return;
    }

    @Override
    public StudyDto.StudyResponseDto getChildStudyList(Long childrenId, StudyDto.StudyRequestDto studyRequestDto) {
        List<Study> studyList = studyRepository.findAllByChildrenIdAndDeadline(childrenId, studyRequestDto.getDeadline());
        return StudyDto.StudyResponseDto.from(studyList);
    }

    @Override
    public StudyDto.StudyResponseDto getParentStudyList(Long parentId, StudyDto.StudyRequestDto studyRequestDto) {
        List<Study> studyList = studyRepository.findAllByChildrenIdAndParentIdAndDeadline(
                studyRequestDto.getChildrenId(), parentId, studyRequestDto.getDeadline());
        return StudyDto.StudyResponseDto.from(studyList);
    }

    @Override
    public StudyDto.StudyResponseDto needCheckStudyList(Long parentId) {
        List<Study> studyList = studyRepository.findAllByParentId(parentId);
        List<Study> needCheck= new ArrayList<>();
        for(Study study:studyList){
            if(study.getIsDone() && study.getPayState()!=StudyState.APPROVAL) needCheck.add(study);
        }
        if(needCheck.isEmpty())
            return null;
        return StudyDto.StudyResponseDto.from(needCheck);
    }

    @Override
    public StudyDto.StudyStateRespDto updateIsDone(Long childrenId, Long studyId, StudyDto.StudyFinishReqDto studyFinishReqDto) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(()->new BusinessException(ErrorMessage.STUDY_NOT_FOUNT));

        study.updateIsDone(true);
        study.decisionGiveMoney(StudyState.WAIT_APPROVAL, studyFinishReqDto.getWantPay());

        return StudyDto.StudyStateRespDto.from(study);
    }

    @Override
    public StudyDto.StudyStateRespDto decisionGiveMoney(Long studyId, Boolean state) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(()->new BusinessException(ErrorMessage.STUDY_NOT_FOUNT));
        if(!study.getIsDone()) // 만약 해당 학습이 완료 되지 않았으면
            throw new BusinessException(ErrorMessage.STUDY_IS_NOT_DONE);
        if(study.getPayState()==StudyState.APPROVAL) // 이미 지급 완료된 학습이면
            throw new BusinessException(ErrorMessage.STUDY_ALREADY_APPROVAL);

        if(!state) {// 학습 반려시
            study.decisionGiveMoney(StudyState.REFUSAL, null);
            study.updateIsDone(false);
        }
        else { // 학습 승인시
            User parent = userRepository.findById(study.getParentId())
                    .orElseThrow(()->new BusinessException(ErrorMessage.PARENT_NOT_FOUND));
            User children = userRepository.findById(study.getChildrenId())
                    .orElseThrow(()->new BusinessException(ErrorMessage.CHILD_NOT_FOUND));
            Connection connection = connectionRepository.findByParentAndChildren(parent, children);

            if(study.getWantPay()==WantPay.KEEP){
                Optional<Goal> goal = goalRepository.findByParentIdAndChildrenIdAndPayIsFalse(
                        study.getParentId(), study.getChildrenId()
                );
                if(goal.isEmpty()) throw  new BusinessException(ErrorMessage.HAVE_NOT_LONG_GOAL);
                goal.get().updateMoney(study.getPayMoney());

                // 장기 목표에 넣었기 때문에 부모 계좌에서만 돈이 빠져나가야 함
                Long accountId = parent.getMainAccount().getId();
                TransactionCreateRequest request = new TransactionCreateRequest(
                        accountId, study.getContent(), study.getPayMoney(), false, children.getName(), parent.getName()
                );
                transactionService.save(request);
            }
            else{
                // 해당 부모에서 자녀로 이체
                transactionService.payStudyMoney(
                        connection.getConnectionId(), study.getContent(), study.getPayMoney());
            }
            study.decisionGiveMoney(StudyState.APPROVAL, null);
        }

        return StudyDto.StudyStateRespDto.from(study);
    }
}
