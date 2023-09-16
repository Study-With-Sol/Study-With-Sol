package com.shbhack.studywithsol.pocketmoney.dto.request;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;

import javax.validation.constraints.NotNull;

public record PocketMoneyCreateRequest(

        /**
         *  childId : 부모자녀 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        @NotNull
        Long childId,

        @NotNull
        Long amount,

        @NotNull
        Integer paymentDate

) {

        public PocketMoney toEntity(){
                return new PocketMoney(
                        this.amount,
                        this.paymentDate
                );
        }

}
