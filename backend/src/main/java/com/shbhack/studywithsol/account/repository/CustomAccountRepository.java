package com.shbhack.studywithsol.account.repository;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.dto.response.AccountListReadResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface CustomAccountRepository {

    Optional<Account> getByIdFetchJoin(Long accountId);

    Optional<Account> findByAccountNumber(String AccountNumber);

    Slice<AccountListReadResponse> getAccountList(Long userId, Pageable pageable);
}
