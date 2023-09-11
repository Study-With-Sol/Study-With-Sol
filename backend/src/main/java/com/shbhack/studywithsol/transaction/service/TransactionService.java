package com.shbhack.studywithsol.transaction.service;

import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import com.shbhack.studywithsol.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionCreateResponse save(TransactionCreateRequest request) {

        Transaction transaction = transactionRepository.save(request.toEntity());

        return TransactionCreateResponse.from(transaction);
    }

    @Transactional(readOnly = true)
    public Slice<TransactionListReadResponse> getTransactionList(Long accountId, Pageable pageable){

        Slice<TransactionListReadResponse> slice = transactionRepository
                .getTransactionList(accountId, pageable);

        return slice;
    }
}
