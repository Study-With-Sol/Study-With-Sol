package com.shbhack.studywithsol.account.dto.response;

public record AccountMainBalanceReadResponse(

        /**
         * id : 주계좌 PK
         * name: 주계좌 이름
         * balance : 주계좌 잔액
         * accountNumber : 계좌 번호
         */

        Long id,
        String name,
        Long balance,
        String accountNumber
) {

    public static AccountMainBalanceReadResponse from(
            Long id,
            String name,
            Long balance,
            String accountNumber
    ){
        return new AccountMainBalanceReadResponse(
                id,
                name,
                balance,
                accountNumber
        );
    }

}
