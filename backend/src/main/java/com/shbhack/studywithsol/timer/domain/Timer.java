package com.shbhack.studywithsol.timer.domain;

import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.utils.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
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

    private int money;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDate studyDate;

    public static Timer from(TimerDto.StudyWithTimerReqDto studyWithTimerReqDto){
        return Timer.builder()
                .parentId(studyWithTimerReqDto.getParentId())
                .childrenId(studyWithTimerReqDto.getChildrenId())
                .content(studyWithTimerReqDto.getContent())
                .time(studyWithTimerReqDto.getTime())
                .payState(TimerState.WAIT)
                .imageUrl(studyWithTimerReqDto.getImageUrl())
                .studyDate(LocalDate.now())
                .build();
    }

    public void paidMoney(int money){
        this.money = money;
        this.payState = TimerState.PAID;
    }
}
