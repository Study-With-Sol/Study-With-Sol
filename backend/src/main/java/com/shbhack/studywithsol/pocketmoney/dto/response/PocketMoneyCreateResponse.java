package com.shbhack.studywithsol.pocketmoney.dto.response;

public record PocketMoneyCreateResponse(

        /**
         *  pocketMoneyId : 용돈 pk
         *  connectionId : 부모자녀 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        Long pocketMoneyId,
        Long connectionId,
        Long amount,
        Integer paymentDate

) {
}
