package com.shbhack.studywithsol.shinhan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Balance {

    /**
     * accountNumber : 출금 계좌 번호
     * balance : 지불 가능 잔액
     */

    private String accountNumber;
    private Long balance;
    
}
