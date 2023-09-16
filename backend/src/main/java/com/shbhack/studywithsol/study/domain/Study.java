package com.shbhack.studywithsol.study.domain;


import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.user.domain.Connection;
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
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Column(nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private Long childrenId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long payMoney;

    @Column(nullable = false)
    private Boolean isDone;

    @Column(nullable = false)
    private StudyState payState;

    @Column(nullable = false)
    private LocalDate deadline;

    private WantPay wantPay;

    public static Study of(Long parentId, StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        return Study.builder()
                .parentId(parentId)
                .childrenId(registerStudyListReqDto.getChildrenId())
                .content(registerStudyListReqDto.getContent())
                .payMoney(registerStudyListReqDto.getPayMoney())
                .deadline(registerStudyListReqDto.getDeadline())
                .isDone(false)
                .payState(StudyState.STUDY)
                .build();
    }

    public void updateIsDone(Boolean isDone){
        this.isDone = isDone;
    }

    public void decisionGiveMoney(StudyState state, WantPay wantPay){
        this.payState = state;
        if(wantPay!=null)
            this.wantPay = wantPay;
    }
}
