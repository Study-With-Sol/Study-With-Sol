package com.shbhack.studywithsol.report.service;

import com.shbhack.studywithsol.report.dto.StudyReportDto;
import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.domain.StudyState;
import com.shbhack.studywithsol.study.repository.StudyRepository;
import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.domain.TimerState;
import com.shbhack.studywithsol.timer.repository.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final StudyRepository studyRepository;
    private final TimerRepository timerRepository;

    @Override
    public List<StudyReportDto.ResponseDto> studyReport(Boolean isChild, StudyReportDto.RequestDto requestDto) {
        List<StudyReportDto.ResponseDto> list = new ArrayList<>();

        LocalDate start = requestDto.getStartDate();
        LocalDate end = requestDto.getEndDate();

        start.datesUntil(end.plusDays(1)).forEach((date)->{
            // 미션 관련 정보 저장
            List<Study> studyList;
            if(isChild){ //학생 요청
                studyList = studyRepository.findAllByChildrenIdAndDeadline(
                        requestDto.getChildrenId(), date);
            } else{ //부모 요청
                studyList = studyRepository.findAllByChildrenIdAndParentIdAndDeadline(
                        requestDto.getParentId(), requestDto.getChildrenId(), date);
            }

            int settingStudy = 0, completeStudy = 0, allowance = 0;
            if(!studyList.isEmpty())
                settingStudy += studyList.size();
            for(Study study:studyList){
                if(study.getPayState()== StudyState.APPROVAL){
                    allowance+=study.getPayMoney();
                    completeStudy++;
                }
            }

            // 자기 주도 학습 관련 정보 저장
            List<Timer> timerList = timerRepository.findAllByChildrenIdAndStudyDate(requestDto.getChildrenId(), date);
            int hours = 0, minutes = 0, seconds = 0;
            for(Timer timer:timerList){
                if(timer.getPayState()== TimerState.PAID){
                    if(!isChild){ //부모 요청
                        if(timer.getParentId().equals(requestDto.getParentId()))
                            allowance+=timer.getMoney();
                    }
                    else allowance+=timer.getMoney(); //자녀 요청

                    String str = timer.getTime();
                    hours += Integer.parseInt(str.split(":")[0]);
                    minutes += Integer.parseInt(str.split(":")[1]);
                    seconds += Integer.parseInt(str.split(":")[2]);
                }
            }
            // 시간 설정
            minutes+=seconds/60;
            seconds = seconds%60;

            hours+=minutes/60;
            minutes = minutes%60;

            String time = hours+":"+minutes+":"+seconds;
            // 리스트 만들기
            list.add(StudyReportDto.ResponseDto.from(settingStudy, completeStudy, allowance, time));
        });

        return list;
    }
}
