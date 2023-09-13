package com.shbhack.studywithsol.account.dto.request;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.domain.enums.AccountType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record AccountCreateRequest(

        /**
         *  owner : "계좌 주인명"
         *  type : "예적금",
         *  accountNumber : "계좌번호",
         *  accountName : "계좌이름",
         *  productName : "상품명",
         *  balance : "잔액",
         *  createdDate : "신규일",
         *  expirationDate : "만기일",
         *  interestRate : "금리(수익률)",
         *  isMainAccount : "주계좌여부"
         *  isActive : 계좌 활성화 여부
         */

        @NotBlank
        String owner,

        @NotBlank
        String type,

        @NotBlank
        String accountNumber,

        @NotBlank
        String accountName,

        @NotBlank
        String productName,

        @NotNull
        Long balance,

        @NotBlank
        String createdDate,

        String expirationDate,

        @NotNull
        Double interestRate,

        @NotNull
        Boolean isMainAccount,

        @NotNull
        Boolean isActive

) {

        public Account toEntity(){
                return new Account(
                        this.owner,
                        AccountType.valueOf(this.type),
                        this.accountNumber,
                        this.accountName,
                        this.productName,
                        this.balance,
                        LocalDate.parse(this.createdDate),
                        LocalDate.parse(this.expirationDate),
                        this.interestRate,
                        this.isActive
                );
        }
}
