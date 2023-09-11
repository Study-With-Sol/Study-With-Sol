package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.Account;

import java.time.format.DateTimeFormatter;

public record AccountRegistrationResponse(
        /**
         *  id : "PK"
         *  owner : "계좌 주인명"
         *  type : "예적금",
         *  accountNumber : "계좌번호",
         *  accountName : "계좌이름",
         *  productName : "상품명",
         *  balance : "잔액(통화별)",
         *  createdDate : "신규일",
         *  expirationDate : "만기일",
         *  interestRate : "금리(수익률)",
         *  isMainAccount : "주계좌여부",
         *  isActive : "계좌 상태"
         */

        Long id,
        String owner,
        String type,
        String accountNumber,
        String accountName,
        String productName,
        Long balance,
        String createdDate,
        String expirationDate,
        Double interestRate,
        Boolean isMainAccount,
        Boolean isActive

) {

    public static AccountRegistrationResponse from(Account account){
        return new AccountRegistrationResponse(
                account.getId(),
                account.getUser().getName(),
                account.getType().getMessage(),
                account.getAccountNumber(),
                account.getAccountName(),
                account.getProductName(),
                account.getBalance(),
                account.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                account.getExpirationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                account.getInterestRate(),
                account.getIsMainAccount(),
                account.getIsActive()
        );
    }
}
