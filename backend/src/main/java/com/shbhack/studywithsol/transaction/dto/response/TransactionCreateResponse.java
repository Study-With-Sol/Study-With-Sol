package com.shbhack.studywithsol.transaction.dto.response;

import com.shbhack.studywithsol.transaction.domain.Transaction;

import java.time.format.DateTimeFormatter;

public record TransactionCreateResponse(

        /**
         *  id : "PK"
         *  account : "계좌 번호"
         *  content : 내역
         *  amount : 금액
         *  useDate : 거래일
         *  isDeposit : 입금/출금
         *  recipient : 받는이
         *  sender : 보내는이
         */

        Long id,
        String accountNumber,
        String content,
        Long amount,
        String useDate,
        Boolean isDeposit,
        String recipient,
        String sender

) {

    public static TransactionCreateResponse from(Transaction transaction){
        return new TransactionCreateResponse(
                transaction.getId(),
                transaction.getAccount().getAccountNumber(),
                transaction.getContent(),
                transaction.getAmount(),
                transaction.getUseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                transaction.getIsDeposit(),
                transaction.getRecipient(),
                transaction.getSender()
        );
    }
}
