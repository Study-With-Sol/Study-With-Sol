package com.shbhack.studywithsol.pocketmoney.dto.request;

import javax.validation.constraints.NotNull;

public record PocketMoneyReadRequest(

        /**
         *  childId : 자녀 pk
         */

        @NotNull
        Long childId

) {
}
