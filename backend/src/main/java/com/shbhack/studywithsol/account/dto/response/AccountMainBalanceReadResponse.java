package com.shbhack.studywithsol.account.dto.response;

public record AccountMainBalanceReadResponse(

        /**
         * id : 주계좌 PK
         * name: 주계좌 이름
         * balance : 주계좌 잔액
         */

        Long id,
        String name,
        Long balance
) {

    public static AccountMainBalanceReadResponse from(
            Long id,
            String name,
            Long balance
    ){
        return new AccountMainBalanceReadResponse(
                id,
                name,
                balance
        );
    }

}
