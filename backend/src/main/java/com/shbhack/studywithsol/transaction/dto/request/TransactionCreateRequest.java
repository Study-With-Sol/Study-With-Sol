package com.shbhack.studywithsol.transaction.dto.request;

import com.shbhack.studywithsol.transaction.domain.Transaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TransactionCreateRequest(

        /**
         *  accountId : 계좌 PK
         *  content : 내역
         *  amount : 금액
         *  isDeposit : 입금/출금
         *  recipient : 받는이
         *  sender : 보내는이
         */

        @NotNull
        Long accountId,

        @NotBlank
        String content,

        @NotNull
        Long amount,

        @NotNull
        Boolean isDeposit,

        @NotBlank
        String recipient,

        @NotBlank
        String sender

) {

    public Transaction toEntity(){
        return new Transaction(
                this.content,
                this.amount,
                this.isDeposit,
                this.recipient,
                this.sender
        );
    }

}
