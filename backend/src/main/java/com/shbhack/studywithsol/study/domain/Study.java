package com.shbhack.studywithsol.study.domain;


import com.shbhack.studywithsol.study.dto.StudyDto;
import com.shbhack.studywithsol.user.domain.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private int payMoney;

    private Boolean isDone;

    private State payState;

    private Boolean isLongGoal;

    @Column(nullable = false)
    private LocalDate deadline;

    public static Study from(StudyDto.RegisterStudyListReqDto registerStudyListReqDto){
        return Study.builder()
                .parentId(registerStudyListReqDto.getParentId())
                .childrenId(registerStudyListReqDto.getChildrenId())
                .content(registerStudyListReqDto.getContent())
                .payMoney(registerStudyListReqDto.getPayMoney())
                .deadline(registerStudyListReqDto.getDeadline())
                .build();
    }
}
