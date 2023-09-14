package com.shbhack.studywithsol.timer.service;

import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.timer.repository.TimerRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService {
    private final TimerRepository timerRepository;

    @Override
    public String studyWithTimer(TimerDto.StudyWithTimerReqDto studyWithTimerReqDto) {
        Timer timer = Timer.from(studyWithTimerReqDto);
        timerRepository.save(timer);
        return "request success";
    }

    @Override
    public TimerDto.ResponseDto paidMoneyAboutTimer(Long studyAmountId, TimerDto.ParentReqDto parentReqDto) {
        int money = parentReqDto.getMoney();
        Optional<Timer> timer = timerRepository.findById(studyAmountId);
        if(timer.isEmpty()){
            throw new BusinessException(ErrorMessage.STUDY_NOT_FOUNT);
        }
        timer.get().paidMoney(money);
        return TimerDto.ResponseDto.of(money, timer.get().getPayState());
    }

}
