package com.shbhack.studywithsol.transaction.repository;

import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomTransactionRepository {

    Slice<TransactionListReadResponse> getTransactionList(Long accountId, Pageable pageable);

}
