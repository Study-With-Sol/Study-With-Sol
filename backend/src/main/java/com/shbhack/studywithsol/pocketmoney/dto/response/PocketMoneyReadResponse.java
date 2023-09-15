package com.shbhack.studywithsol.pocketmoney.dto.response;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;

public record PocketMoneyReadResponse(

        /**
         *  pocketMoneyId : 용돈 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         */

        Long pocketMoneyId,
        Long amount,
        Integer paymentDate

) {

    public static PocketMoneyReadResponse from(PocketMoney pocketMoney){
        return new PocketMoneyReadResponse(
                pocketMoney.getId(),
                pocketMoney.getAmount(),
                pocketMoney.getPaymentDate()
        );
    }
    
}
