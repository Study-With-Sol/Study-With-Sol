package com.shbhack.studywithsol.study.controller;

import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.study.service.StudyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/study")
public class StudyController {
    private final StudyService studyService;

    @ApiOperation(value ="자녀 학습 등록")
    @PostMapping
    public ResponseEntity<String> registerStudyList(Authentication authentication, @RequestBody StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        Long parentId = Long.valueOf(authentication.getName());
        String str = studyService.registerStudyList(parentId, registerStudyListReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @ApiOperation(value ="자녀가 본인의 학습 리스트 조회")
    @PostMapping("/list")
    public ResponseEntity<StudyDto.StudyResponseDto> getChildStudyList(Authentication authentication, @RequestBody StudyDto.StudyRequestDto studyRequestDto){
        Long childrenId = Long.valueOf(authentication.getName());
        StudyDto.StudyResponseDto studyList = studyService.getChildStudyList(childrenId, studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    @ApiOperation(value ="부모가 본인이 등록한 학습 리스트 조회")
    @PostMapping("/list/parent")
    public ResponseEntity<StudyDto.StudyResponseDto> getParentStudyList(Authentication authentication, @RequestBody StudyDto.StudyRequestDto studyRequestDto){
        Long parentId = Long.valueOf(authentication.getName());
        StudyDto.StudyResponseDto studyList = studyService.getParentStudyList(parentId, studyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    @ApiOperation(value ="부모 메인에 보이는 용돈 요청 리스트")
    @PostMapping("/list/need-check")
    public ResponseEntity<StudyDto.StudyResponseDto> needCheckStudyList(
            Authentication authentication){
        Long parentId = Long.valueOf(authentication.getName());
        StudyDto.StudyResponseDto studyList = studyService.needCheckStudyList(parentId);
        return ResponseEntity.status(HttpStatus.OK).body(studyList);
    }

    @ApiOperation(value ="학습 하나 삭제")
    @DeleteMapping("/{studyId}")
    public ResponseEntity<String> deleteOne(@PathVariable Long studyId){
        studyService.deleteOne(studyId);
        return ResponseEntity.status(HttpStatus.OK).body("delete success"); //삭제한 학습의 내용 return
    }

    @ApiOperation(value ="자녀가 학습 완료 전송")
    @PatchMapping("/{studyId}")
    public ResponseEntity<StudyDto.StudyStateRespDto> updateIsDone(
            Authentication authentication, @PathVariable Long studyId, @RequestBody StudyDto.StudyFinishReqDto studyFinishReqDto){
        Long childrenId = Long.valueOf(authentication.getName());
        StudyDto.StudyStateRespDto studyStateRespDto = studyService.updateIsDone(childrenId, studyId, studyFinishReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(studyStateRespDto);
    }

    // 부모가 완료된 학습의 용돈 지급 여부 결정
    @ApiOperation(value ="전송된 학습에 대해 용돈 지급 여부 결정")
    @PatchMapping("/money/{studyId}")
    public ResponseEntity<StudyDto.StudyStateRespDto>  decisionGiveMoney(@PathVariable Long studyId, @RequestBody Map<String, Boolean> state){
        StudyDto.StudyStateRespDto studyStateRespDto = studyService.decisionGiveMoney(studyId, state.get("state"));
        return ResponseEntity.status(HttpStatus.OK).body(studyStateRespDto);
    }
}
