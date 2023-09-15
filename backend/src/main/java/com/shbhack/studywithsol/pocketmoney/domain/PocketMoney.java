package com.shbhack.studywithsol.pocketmoney.domain;

import com.shbhack.studywithsol.user.domain.Connection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocketMoney {

    /**
     *
     *  id : PK
     *  connection : 부모-자녀
     *  amount : 용돈
     *  paymentDate : 지급일
     *  paymentStatus : 지급상태
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "connection_id")
    private Connection connection;

    @Column
    private Long amount;

    @Column
    private Integer paymentDate;

    @Column(nullable = false)
    private Boolean paymentStatus;

}
