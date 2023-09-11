package com.shbhack.studywithsol.account.repository;

import com.shbhack.studywithsol.account.domain.Account;

import java.util.Optional;

public interface CustomAccountRepository {

    Optional<Account> getByIdFetchJoin(Long accountId);

    Optional<Account> findByAccountNumber(String AccountNumber);
}
