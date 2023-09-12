package com.shbhack.studywithsol.study.service;

import com.shbhack.studywithsol.study.domain.Study;
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
        String content = studyRepository.findById(studyId).get().getContent();
        studyRepository.deleteById(studyId);
        return content;
    }

    @Override
    public StudyDto.StudyResponseDto getStudyList(StudyDto.StudyRequestDto studyRequestDto) {
        List<Study> studyList;
        if(studyRequestDto.getParentId()==null){
            studyList = studyRepository.findAllByChildrenId(studyRequestDto.getChildrenId());
        } else{
            studyList = studyRepository.findAllByChildrenIdAndParentId(studyRequestDto.getChildrenId(), studyRequestDto.getParentId());
        }
        return StudyDto.StudyResponseDto.from(studyList);
    }
}
