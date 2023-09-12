package com.shbhack.studywithsol.transaction.dto.response;

import com.shbhack.studywithsol.transaction.domain.Transaction;

import java.time.format.DateTimeFormatter;

public record TransactionListReadResponse(

        /**
         *  id : "PK"
         *  content : 내역
         *  amount : 금액
         *  useDate : 거래일
         *  isDeposit : 입금/출금
         */

        Long id,
        String content,
        Long amount,
        String useDate,
        Boolean isDeposit

) {

    public static TransactionListReadResponse of(Transaction transaction) {
        return new TransactionListReadResponse(
                transaction.getId(),
                transaction.getContent(),
                transaction.getAmount(),
                transaction.getUseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                transaction.getIsDeposit()
        );
    }

}
