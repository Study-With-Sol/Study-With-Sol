package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.enums.AccountType;

public record AccountReadResponse(

        /**
         *  id : "PK"
         *  owner : "계좌 주인명"
         *  type : "예적금",
         *  accountNumber : "계좌번호",
         *  accountName : "계좌이름",
         *  productName : "상품명",
         *  balance : "잔액(통화별)",
         *  isMainAccount : "주계좌여부",
         */

        Long id,
        String owner,
        String type,
        String accountNumber,
        String accountName,
        String productName,
        Long balance,
        Boolean isMainAccount

) {
    public static AccountReadResponse from(
            Long id,
            String owner,
            AccountType type,
            String accountNumber,
            String accountName,
            String productName,
            Long balance,
            Boolean isMainAccount
    ){
        return new AccountReadResponse(
                id,
                owner,
                type.getMessage(),
                accountNumber,
                accountName,
                productName,
                balance,
                isMainAccount
        );
    }
}
