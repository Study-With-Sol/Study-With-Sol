package com.shbhack.studywithsol.timer.service;

import com.shbhack.studywithsol.timer.dto.TimerDto;

import java.util.List;

public interface TimerService {
    String studyWithTimer(Long childrenId, TimerDto.StudyWithTimerReqDto studyWithTimerReqDto);

    List<TimerDto.ListResDto> getListTimer(Long parentId, Long childrenId);

    TimerDto.ResponseDto paidMoneyAboutTimer(Long studyAmountId, TimerDto.ParentReqDto parentReqDto);
}
