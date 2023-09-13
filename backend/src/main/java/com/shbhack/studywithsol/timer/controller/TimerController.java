package com.shbhack.studywithsol.timer.controller;

import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.timer.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/timer")
public class TimerController {
    private final TimerService timerService;
    @PostMapping
    public ResponseEntity<String> studyWithTimer(@RequestBody TimerDto.StudyWithTimerReqDto studyWithTimerReqDto){
        String str = timerService.studyWithTimer(studyWithTimerReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @PatchMapping("/{studyAmountId}")
    public ResponseEntity<TimerDto.ResponseDto> paidMoneyAboutTimer(@PathVariable Long studyAmountId, @RequestBody TimerDto.ParentReqDto parentReqDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                timerService.paidMoneyAboutTimer(studyAmountId, parentReqDto)
        );
    }
}
