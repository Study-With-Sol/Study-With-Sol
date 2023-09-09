package com.shbhack.studywithsol.study.domain;


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

    @Column(nullable = false)
    private Boolean isLongGoal;

    @Column(nullable = false)
    private LocalDate deadline;
}
