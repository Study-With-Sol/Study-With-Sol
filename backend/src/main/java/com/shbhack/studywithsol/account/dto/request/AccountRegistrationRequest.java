package com.shbhack.studywithsol.account.dto.request;

public record AccountRegistrationRequest(

        /**
         *  accountNumber : "계좌번호",
         *  isMainAccount : "주계좌여부"
         */

        String accountNumber,
        Boolean isMainAccount
) {
}
