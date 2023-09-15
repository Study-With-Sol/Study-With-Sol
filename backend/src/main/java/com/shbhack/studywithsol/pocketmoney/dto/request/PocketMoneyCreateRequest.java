package com.shbhack.studywithsol.pocketmoney.dto.request;

import javax.validation.constraints.NotNull;

public record PocketMoneyCreateRequest(

        /**
         *  connectionId : 부모자녀 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        @NotNull
        Long connectionId,

        @NotNull
        Long amount,

        @NotNull
        Integer paymentDate

) {
}
