package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.study.dto.StudyDto;

public interface StudyService {
    String registerStudyList(Long parentId, StudyDto.RegisterStudyListReqDto registerStudyListReqDto);

    void deleteOne(Long studyId);

    StudyDto.StudyResponseDto getChildStudyList(Long childrenId, StudyDto.StudyRequestDto studyRequestDto);

    StudyDto.StudyResponseDto getParentStudyList(Long parentId, StudyDto.StudyRequestDto studyRequestDto);

    StudyDto.StudyResponseDto needCheckStudyList(Long parentId);

    StudyDto.StudyStateRespDto updateIsDone(Long childrenId, Long studyId, StudyDto.StudyFinishReqDto studyFinishReqDto);

    StudyDto.StudyStateRespDto decisionGiveMoney(Long studyId, Boolean state);
}
