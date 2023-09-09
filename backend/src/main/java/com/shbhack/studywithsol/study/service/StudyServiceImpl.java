package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService{
    private final StudyRepository studyRepository;

    @Override
    public String deleteList(StudyDto.StudyRequestDto studyRequestDto) {
        Long childrenId = studyRequestDto.getChildrenId();
        Long parentId = studyRequestDto.getParentId();
        LocalDate deadline = studyRequestDto.getDeadline();
        studyRepository.deleteAllByChildrenIdAndParentIdAndDeadline(childrenId, parentId, deadline);
        return "delete success";
    }

    @Override
    public List<StudyDto.StudyResponseDto> getChildStudyList(StudyDto.StudyRequestDto studyRequestDto) {
        return null;
    }

    @Override
    public List<StudyDto.StudyResponseDto> getStudyList(StudyDto.StudyRequestDto studyRequestDto) {
        return null;
    }
}
