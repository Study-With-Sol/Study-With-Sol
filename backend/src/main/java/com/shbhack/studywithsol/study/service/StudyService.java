package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.study.dto.StudyDto;

import java.util.List;

public interface StudyService {
    String deleteList(StudyDto.StudyRequestDto studyRequestDto);

    List<StudyDto.StudyResponseDto> getChildStudyList(StudyDto.StudyRequestDto studyRequestDto);

    List<StudyDto.StudyResponseDto> getStudyList(StudyDto.StudyRequestDto studyRequestDto);
}
