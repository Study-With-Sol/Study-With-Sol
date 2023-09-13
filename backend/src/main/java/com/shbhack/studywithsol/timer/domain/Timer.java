package com.shbhack.studywithsol.timer.domain;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
    private State payState;

    private int money;

    private String imageUrl;

    public static Timer from(TimerDto.StudyWithTimerReqDto studyWithTimerReqDto){
        return Timer.builder()
                .parentId(studyWithTimerReqDto.getParentId())
                .childrenId(studyWithTimerReqDto.getChildrenId())
                .content(studyWithTimerReqDto.getContent())
                .time(studyWithTimerReqDto.getTime())
                .payState(State.WAIT)
                .imageUrl(studyWithTimerReqDto.getImageUrl())
                .build();
    }

    public void paidMoney(int money){
        this.money = money;
        this.payState = State.PAID;
    }
}
