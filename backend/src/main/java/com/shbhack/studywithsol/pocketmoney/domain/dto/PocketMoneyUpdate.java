package com.shbhack.studywithsol.pocketmoney.domain.dto;

public record PocketMoneyUpdate(

        /**
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        Long amount,
        Integer paymentDate

) {
}
