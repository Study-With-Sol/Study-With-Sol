package com.shbhack.studywithsol.account.service;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.response.AccountCreateResponse;
import com.shbhack.studywithsol.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountCreateResponse save(AccountCreateRequest request) {

        Account account = accountRepository.save(request.toEntity());

        return AccountCreateResponse.from(account);
    }
}
