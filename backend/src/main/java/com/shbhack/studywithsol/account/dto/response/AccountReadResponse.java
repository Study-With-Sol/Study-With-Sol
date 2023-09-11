package com.shbhack.studywithsol.account.dto.response;

import com.shbhack.studywithsol.account.domain.enums.AccountType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record AccountReadResponse(

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
    public static AccountReadResponse from(
            Long id,
            String owner,
            AccountType type,
            String accountNumber,
            String accountName,
            String productName,
            Long balance,
            LocalDate createdDate,
            LocalDate expirationDate,
            Double interestRate,
            Boolean isMainAccount,
            Boolean isActive
    ){
        return new AccountReadResponse(
                id,
                owner,
                type.getMessage(),
                accountNumber,
                accountName,
                productName,
                balance,
                createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                interestRate,
                isMainAccount,
                isActive
        );
    }
}
