package com.shbhack.studywithsol.transaction.service;

import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
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
}
