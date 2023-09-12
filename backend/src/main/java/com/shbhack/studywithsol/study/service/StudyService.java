package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.dto.StudyDto;

import java.util.List;

public interface StudyService {
    String registerStudyList(StudyDto.RegisterStudyListReqDto registerStudyListReqDto);
    String deleteList(StudyDto.StudyRequestDto studyRequestDto);

    String deleteOne(Long studyId);

    StudyDto.StudyResponseDto getStudyList(StudyDto.StudyRequestDto studyRequestDto);
}
