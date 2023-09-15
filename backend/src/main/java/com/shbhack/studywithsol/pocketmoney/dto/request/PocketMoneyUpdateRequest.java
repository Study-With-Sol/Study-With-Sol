package com.shbhack.studywithsol.pocketmoney.dto.request;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;
import com.shbhack.studywithsol.pocketmoney.domain.dto.PocketMoneyUpdate;

import javax.validation.constraints.NotNull;

public record PocketMoneyUpdateRequest(

        /**
         *  pocketMoneyId : 용돈 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        @NotNull
        Long pocketMoneyId,

        @NotNull
        Long amount,

        @NotNull
        Integer paymentDate

) {

    public PocketMoneyUpdate toDto(){
        return new PocketMoneyUpdate(
                this.amount,
                this.paymentDate
        );
    }

}
