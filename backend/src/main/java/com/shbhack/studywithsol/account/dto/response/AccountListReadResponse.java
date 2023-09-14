package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.Account;

public record AccountListReadResponse(

        /**
         *  id : "PK"
         *  accountNumber : "계좌번호",
         *  accountName : "계좌이름",
         *  balance : "잔액(통화별)",
         *  isMainAccount : "주계좌여부",
         */

        Long id,
        String accountNumber,
        String accountName,
        Long balance,
        Boolean isMainAccount

) {

    public static AccountListReadResponse of(Account account) {
        return new AccountListReadResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountName(),
                account.getBalance(),
                account.getIsMainAccount()
        );
    }

}
