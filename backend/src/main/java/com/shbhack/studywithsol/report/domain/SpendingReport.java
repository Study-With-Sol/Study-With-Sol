package com.shbhack.studywithsol.report.domain;

import com.shbhack.studywithsol.user.domain.Connection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpendingReport {

    /**
     *
     *  id : PK
     *  connection : 부모-자녀
     *  year : 년
     *  month : 월
     *  content : 내용
     *  percent : 비율
     *  amount : 금액
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Connection connection;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Double percent;

    @Column(nullable = false)
    private Long amount;

}
