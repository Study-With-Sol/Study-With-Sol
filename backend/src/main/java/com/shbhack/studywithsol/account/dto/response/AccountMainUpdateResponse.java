package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.Account;

public record AccountMainUpdateResponse(

        /**
         *  id : "계좌PK"
         */

        Long accountId

) {
    public static AccountMainUpdateResponse from(Account account){
        return new AccountMainUpdateResponse(
                account.getId()
        );
    }
}
