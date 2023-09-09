package com.shbhack.studywithsol.study.controller;

import com.shbhack.studywithsol.study.dto.StudyDto;
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


    @PostMapping("/parent")
    public ResponseEntity<List<StudyDto.StudyResponseDto>> getChildStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        List<StudyDto.StudyResponseDto> studyList = studyService.getChildStudyList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    @PostMapping("/child")
    public ResponseEntity<List<StudyDto.StudyResponseDto>> getStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        List<StudyDto.StudyResponseDto> studyList = studyService.getStudyList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        String str = studyService.deleteList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }
}
