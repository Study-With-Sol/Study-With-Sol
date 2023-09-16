package com.shbhack.studywithsol.account.dto.response;

public record AccountOwnerReadResponse(

        /**
         * owner : 예금주
         * accountName : 계좌 이름
         */

        String owner,
        String accountName

) {

    public static AccountOwnerReadResponse from(
            String owner,
            String accountName
    ) {
        return new AccountOwnerReadResponse(
                owner,
                accountName
        );
    }
    
}
