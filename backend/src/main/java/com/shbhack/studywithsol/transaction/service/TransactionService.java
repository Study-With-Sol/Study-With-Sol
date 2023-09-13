package com.shbhack.studywithsol.transaction.service;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.repository.AccountRepository;
import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import com.shbhack.studywithsol.transaction.repository.TransactionRepository;
import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.user.repository.UserRepository;
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
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    public TransactionCreateResponse save(TransactionCreateRequest request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        Transaction transaction = transactionRepository.save(request.toEntity());

        transaction.beTradedIn(account);

        account.updateBalance(request.amount(), request.isDeposit());

        return TransactionCreateResponse.from(transaction);
    }

    public void payStudyMoney(Long connectionId, String content, Long amount) {

        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.CONNECTION_NOT_FOUND)));

        User parent = userRepository.findById(connection.getParent().getUserId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.USER_NOT_FOUND)));

        User child = userRepository.findById(connection.getChildren().getUserId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.USER_NOT_FOUND)));

        Account parentAccount = accountRepository.findById(parent.getMainAccount().getId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        Account childAccount = accountRepository.findById(child.getMainAccount().getId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        Transaction parentTransaction = new Transaction(content, amount, false,
                connection.getChildren().getName(), connection.getParent().getName());

        parentTransaction.beTradedIn(parentAccount);

        parentAccount.updateBalance(amount, parentTransaction.getIsDeposit());

        transactionRepository.save(parentTransaction);

        Transaction childTransaction = new Transaction(content, amount, true,
                connection.getChildren().getName(), connection.getParent().getName());

        childTransaction.beTradedIn(childAccount);

        childAccount.updateBalance(amount, childTransaction.getIsDeposit());

        transactionRepository.save(childTransaction);
    }

    @Transactional(readOnly = true)
    public Slice<TransactionListReadResponse> getTransactionList(Long accountId, Pageable pageable){

        Slice<TransactionListReadResponse> slice = transactionRepository
                .getTransactionList(accountId, pageable);

        return slice;
    }
}
