package com.shbhack.studywithsol.transaction.dto.request;

import com.shbhack.studywithsol.transaction.domain.Transaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TransactionCreateRequest(

        /**
         *  account : 연결계좌
         *  content : 내역
         *  amount : 금액
         *  isDeposit : 입금/출금
         *  recipient : 받는이
         *  sender : 보내는이
         */

        @NotBlank
        String account,

        @NotBlank
        String content,

        @NotNull
        Long amount,

        @NotNull
        Boolean isDepository,

        @NotBlank
        String recipient,

        @NotBlank
        String sender

) {

    public Transaction toEntity(){
        return new Transaction(
                this.account,
                this.content,
                this.amount,
                this.isDepository,
                this.recipient,
                this.sender
        );
    }

}
