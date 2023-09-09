package com.shbhack.studywithsol.study.domain;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name=)

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int payMoney;

    private Boolean isDone;

    private State payState;

    private Boolean isLongGoal;

    @Column(nullable = false)
    private LocalDateTime deadline;
}
