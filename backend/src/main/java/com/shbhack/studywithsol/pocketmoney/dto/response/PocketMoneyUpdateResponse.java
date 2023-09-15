package com.shbhack.studywithsol.pocketmoney.dto.response;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;

public record PocketMoneyUpdateResponse(

        /**
         *  pocketMoneyId : 용돈 pk
         *  connectionId : 부모자녀 pk
         *  amount : 용돈
         *  paymentDate : 지급일
         *  paymentStatus : 용돈 지급 상태
         */

        Long pocketMoneyId,
        Long connectionId,
        Long amount,
        Integer paymentDate,
        Boolean paymentStatus

) {

    public static PocketMoneyUpdateResponse from(PocketMoney pocketMoney){
        return new PocketMoneyUpdateResponse(
                pocketMoney.getId(),
                pocketMoney.getConnection().getConnectionId(),
                pocketMoney.getAmount(),
                pocketMoney.getPaymentDate(),
                pocketMoney.getPaymentStatus()
        );
    }

}
