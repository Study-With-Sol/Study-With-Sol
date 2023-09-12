package com.shbhack.studywithsol.study.controller;

import com.shbhack.studywithsol.study.domain.State;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.service.StudyService;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/study")
public class StudyController {
    private final StudyService studyService;
    private final TransactionService transactionService;

    // 자녀에게 학습 등록
    @PostMapping
    public ResponseEntity<String> registerStudyList(@RequestBody StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        String str = studyService.registerStudyList(registerStudyListReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    // 학습 리스트 조회
    @PostMapping("/list")
    public ResponseEntity<StudyDto.StudyResponseDto> getStudyList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        StudyDto.StudyResponseDto studyList = studyService.getStudyList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    // 해당 날짜의 자녀 학습 전부 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteList(@RequestBody StudyDto.StudyRequestDto studyRequestDto){
        String str = studyService.deleteList(studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(str); // "delete success"
    }

    // 해당 아이디 학습 하나 삭제
    @DeleteMapping("/{studyId}")
    public ResponseEntity<String> deleteOne(@PathVariable Long studyId){
        String content = studyService.deleteOne(studyId);
        return ResponseEntity.status(HttpStatus.OK).body(content+" delete success"); //삭제한 학습의 내용 return
    }

    // 자녀가 목록 완료 표시
    @PatchMapping("/{studyId}")
    public ResponseEntity<StudyDto.StudyStateRespDto> updateIsDone(@PathVariable Long studyId){
        StudyDto.StudyStateRespDto studyStateRespDto = studyService.updateIsDone(studyId);
        return ResponseEntity.status(HttpStatus.OK).body(studyStateRespDto);
    }

    // 부모가 완료된 학습의 용돈 지급 여부 결정
    @PatchMapping("/money/{studyId}")
    public ResponseEntity<StudyDto.StudyStateRespDto>  decisionGiveMoney(@PathVariable Long studyId, @RequestBody Map<String, Boolean> state){
        StudyDto.StudyStateRespDto studyStateRespDto = studyService.decisionGiveMoney(studyId, state.get("state"));
        if(!state.get("state")){
            studyStateRespDto = studyService.updateIsDone(studyId);
        }
        // 승인 즉 APPROVAL, state = true 가 들어올 떄 바로 지급
//        else{
//            transactionService.save();
//        }
        return ResponseEntity.status(HttpStatus.OK).body(studyStateRespDto);
    }
}
