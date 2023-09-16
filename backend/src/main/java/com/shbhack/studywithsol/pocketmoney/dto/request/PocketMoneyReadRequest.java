package com.shbhack.studywithsol.pocketmoney.dto.request;

import javax.validation.constraints.NotNull;

public record PocketMoneyReadRequest(

        /**
         *  connectionId : 부모자녀 pk
         */

        @NotNull
        Long connectionId

) {
}
