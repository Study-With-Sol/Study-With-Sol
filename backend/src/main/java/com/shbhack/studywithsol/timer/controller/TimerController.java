package com.shbhack.studywithsol.timer.controller;

import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.timer.service.TimerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/timer")
public class TimerController {
    private final TimerService timerService;

    @ApiOperation(value ="자녀 타이머 공부 시간 전송")
    @PostMapping
    public ResponseEntity<String> studyWithTimer(
            Authentication authentication, @RequestBody TimerDto.StudyWithTimerReqDto studyWithTimerReqDto){
        Long childrenId = Long.valueOf(authentication.getName());
        String str = timerService.studyWithTimer(childrenId, studyWithTimerReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @ApiOperation(value = "부모한테 전송된 공부 시간 확인")
    @GetMapping
    public ResponseEntity<List<TimerDto.ListResDto>> getListTimer(Authentication authentication, @RequestBody TimerDto.ListReqDto listReqDto){
        Long parentId = Long.valueOf(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(timerService.getListTimer(parentId, listReqDto.getChildrenId()));
    }


    @ApiOperation(value ="타이머 공부 시간에 대한 용돈 지급")
    @PatchMapping("/{studyAmountId}")
    public ResponseEntity<TimerDto.ResponseDto> paidMoneyAboutTimer(@PathVariable Long studyAmountId, @RequestBody TimerDto.ParentReqDto parentReqDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                timerService.paidMoneyAboutTimer(studyAmountId, parentReqDto)
        );
    }
}
