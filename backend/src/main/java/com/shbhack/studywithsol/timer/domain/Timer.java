package com.shbhack.studywithsol.timer.domain;

import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyAmountId;

    @Column(nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private Long childrenId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private TimerState payState;

    private Long money;

    @Column(nullable = false)
    private LocalDate studyDate;

    private WantPay wantPay;

    public static Timer of(Long childrenId, TimerDto.StudyWithTimerReqDto studyWithTimerReqDto){
        return Timer.builder()
                .parentId(studyWithTimerReqDto.getParentId())
                .childrenId(childrenId)
                .content(studyWithTimerReqDto.getContent())
                .time(studyWithTimerReqDto.getTime())
                .payState(TimerState.WAIT)
                .studyDate(LocalDate.now())
                .wantPay(studyWithTimerReqDto.getWantPay())
                .build();
    }

    public void paidMoney(Long money){
        this.money = money;
        this.payState = TimerState.PAID;
    }
}
