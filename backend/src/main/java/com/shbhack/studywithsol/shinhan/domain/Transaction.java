package com.shbhack.studywithsol.shinhan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    /**
     * transactionDate : 거래일자
     * transactionTime : 거래시간
     * summary : 적요
     * withdrawal : 출금금액
     * deposit : 입금금액
     * content : 내용
     * balance : 잔액
     * classification : 입지구분
     * transactionPoint : 거래점명
     */

    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private String summary;
    private Long withdrawal;
    private Long deposit;
    private String content;
    private Long balance;
    private Integer classification;
    private String transactionPoint;

}
