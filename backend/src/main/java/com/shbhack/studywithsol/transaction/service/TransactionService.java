package com.shbhack.studywithsol.transaction.service;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.repository.AccountRepository;
import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import com.shbhack.studywithsol.transaction.repository.TransactionRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
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
    private final AccountRepository accountRepository;


    public TransactionCreateResponse save(TransactionCreateRequest request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        Transaction transaction = transactionRepository.save(request.toEntity());

        transaction.beTradedIn(account);

        return TransactionCreateResponse.from(transaction);
    }

    @Transactional(readOnly = true)
    public Slice<TransactionListReadResponse> getTransactionList(Long accountId, Pageable pageable){

        Slice<TransactionListReadResponse> slice = transactionRepository
                .getTransactionList(accountId, pageable);

        return slice;
    }
}
