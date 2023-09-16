package com.shbhack.studywithsol.goal.domain;

import com.shbhack.studywithsol.goal.dto.GoalDto;
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
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @Column(nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private Long childrenId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long money;

    @Column(nullable = false)
    private Boolean pay;

    @Column(nullable = false)
    private LocalDate deadline;

    public static Goal of(Long parentId, GoalDto.GoalReqDto goalReqDto){
        return Goal.builder()
                .parentId(parentId)
                .childrenId(goalReqDto.getChildrenId())
                .content(goalReqDto.getContent())
                .money(0L)
                .pay(false)
                .deadline(goalReqDto.getDeadline())
                .build();
    }

    public void updatePay(){
        this.pay = true;
    }

    public void updateMoney(Long money){
        this.money = this.money + money;
    }
}
