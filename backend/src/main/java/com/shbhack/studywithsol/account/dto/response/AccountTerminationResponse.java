package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.Account;

import java.time.format.DateTimeFormatter;

public record AccountTerminationResponse(

        /**
         *  id : 계좌 pk
         */

        Long id

) {

    public static AccountTerminationResponse from(Account account){
        return new AccountTerminationResponse(
                account.getId()
        );
    }

}
