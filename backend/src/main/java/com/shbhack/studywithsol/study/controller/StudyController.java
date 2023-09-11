package com.shbhack.studywithsol.study.controller;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.repository.StudyRepository;
import com.shbhack.studywithsol.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;
    private final StudyRepository studyRepository;

    // 부모가 해당 자녀에게 등록한 학습 조회
    @PostMapping("/parent")
    public ResponseEntity<List<StudyDto.StudyResponseDto>> getChildStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        List<StudyDto.StudyResponseDto> studyList = studyService.getChildStudyList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 자녀가 가진 모든 학습 조회
    @PostMapping("/child")
    public ResponseEntity<List<StudyDto.StudyResponseDto>> getStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        List<StudyDto.StudyResponseDto> studyList = studyService.getStudyList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 자녀에게 학습 등록
    @PostMapping
    public ResponseEntity<StudyDto.StudyResponseDto> registStudyList(@RequestBody StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        Study study = Study.from(registerStudyListReqDto);
        studyRepository.save(study);
        return ResponseEntity.status(HttpStatus.OK).body(StudyDto.StudyResponseDto.from(study));
    }

    // 해당 날짜의 자녀 학습 전부 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        String str = studyService.deleteList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @DeleteMapping("/{studyId}")
    public ResponseEntity<String> deleteStudyOne(@PathVariable Long studyId){
        String content = studyRepository.findById(studyId).get().getContent();
        studyRepository.deleteById(studyId);
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
