package com.shbhack.studywithsol.account.dto.response;

public record AccountOwnerReadResponse(

        /**
         * check : 본인 일치 여부
         */

        Boolean check

) {

    public static AccountOwnerReadResponse from(
            Boolean check
    ) {
        return new AccountOwnerReadResponse(
                check
        );
    }

}
