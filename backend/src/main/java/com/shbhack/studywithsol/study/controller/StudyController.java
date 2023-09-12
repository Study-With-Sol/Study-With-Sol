package com.shbhack.studywithsol.study.controller;

import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping(value = "/study")
public class StudyController {
    private final StudyService studyService;

    // 자녀에게 학습 등록
    @PostMapping("/study")
    public ResponseEntity<String> registerStudyList(@RequestBody StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        String str = studyService.registerStudyList(registerStudyListReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    // 학습 리스트 조회
    @PostMapping("/study/list")
    public ResponseEntity<StudyDto.StudyResponseDto> getStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        System.out.println("들어왔다ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ");
        StudyDto.StudyResponseDto studyList = studyService.getStudyList(studyRequestDto);
        System.out.println("리스트가 문제일까?");
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 해당 날짜의 자녀 학습 전부 삭제
    @DeleteMapping("/study")
    public ResponseEntity<String> deleteList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        String str = studyService.deleteList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(str); // "delete success"
    }

    // 해당 아이디 학습 하나 삭제
    @DeleteMapping("/study/{studyId}")
    public ResponseEntity<String> deleteOne(@PathVariable Long studyId){
        String content = studyService.deleteOne(studyId);
        return ResponseEntity.status(HttpStatus.OK).body(content+" delete success"); //삭제한 학습의 내용 return
    }
}
