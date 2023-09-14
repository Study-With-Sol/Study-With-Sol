package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.domain.StudyState;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.repository.StudyRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService{
    private final StudyRepository studyRepository;

    @Override
    public String registerStudyList(StudyDto.RegisterStudyListReqDto registerStudyListReqDto) {
        Study study = Study.from(registerStudyListReqDto);
        studyRepository.save(study);
        return "register success";
    }

    @Override
    public String deleteList(StudyDto.StudyRequestDto studyRequestDto) {
        Long childrenId = studyRequestDto.getChildrenId();
        Long parentId = studyRequestDto.getParentId();
        LocalDate deadline = studyRequestDto.getDeadline();
        studyRepository.deleteAllByChildrenIdAndParentIdAndDeadline(childrenId, parentId, deadline);
        return "delete success";
    }

    @Override
    public String deleteOne(Long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);
        if(study.isEmpty()){
            throw new BusinessException(ErrorMessage.STUDY_NOT_FOUNT);
        }
        String content = study.get().getContent();
        studyRepository.deleteById(studyId);
        return content;
    }

    @Override
    public StudyDto.StudyResponseDto getStudyList(StudyDto.StudyRequestDto studyRequestDto) {
        List<Study> studyList;
        if(studyRequestDto.getParentId()==null)
            studyList = studyRepository.findAllByChildrenId(studyRequestDto.getChildrenId());
        else
            studyList = studyRepository.findAllByChildrenIdAndParentId(studyRequestDto.getChildrenId(), studyRequestDto.getParentId());

        return StudyDto.StudyResponseDto.from(studyList);
    }

    @Override
    public StudyDto.StudyStateRespDto updateIsDone(Long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);
        if(study.isEmpty()) throw new BusinessException(ErrorMessage.STUDY_NOT_FOUNT);

        study.get().updateIsDone(!study.get().getIsDone());

        if(study.get().getIsDone()) study.get().decisionGiveMoney(StudyState.WAIT_APPROVAL);

        return StudyDto.StudyStateRespDto.from(study.get());
    }

    @Override
    public StudyDto.StudyStateRespDto decisionGiveMoney(Long studyId, Boolean state) {
        Optional<Study> study = studyRepository.findById(studyId);
        if(study.isEmpty()) throw new BusinessException(ErrorMessage.STUDY_NOT_FOUNT);
        if(!study.get().getIsDone())
            throw new BusinessException(ErrorMessage.STUDY_IS_NOT_DONE);
        if(study.get().getPayState()==StudyState.APPROVAL)
            throw new BusinessException(ErrorMessage.STUDY_ALREADY_APPROVAL);

        if(!state) study.get().decisionGiveMoney(StudyState.REFUSAL);
        else study.get().decisionGiveMoney(StudyState.APPROVAL);

        return StudyDto.StudyStateRespDto.from(study.get());
    }
}
