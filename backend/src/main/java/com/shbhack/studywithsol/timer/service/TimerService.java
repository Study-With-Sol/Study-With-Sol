package com.shbhack.studywithsol.timer.service;

import com.shbhack.studywithsol.timer.domain.State;
import com.shbhack.studywithsol.timer.dto.TimerDto;

public interface TimerService {
    String studyWithTimer(TimerDto.StudyWithTimerReqDto studyWithTimerReqDto);

    TimerDto.ResponseDto paidMoneyAboutTimer(Long studyAmountId, TimerDto.ParentReqDto parentReqDto);
}
