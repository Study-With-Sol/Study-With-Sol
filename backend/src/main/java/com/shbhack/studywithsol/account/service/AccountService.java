package com.shbhack.studywithsol.account.service;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountRegistrationRequest;
import com.shbhack.studywithsol.account.dto.response.AccountCreateResponse;
import com.shbhack.studywithsol.account.dto.response.AccountReadResponse;
import com.shbhack.studywithsol.account.dto.response.AccountRegistrationResponse;
import com.shbhack.studywithsol.account.repository.AccountRepository;

import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountRegistrationResponse registration(AccountRegistrationRequest request, Long userId) {

        Account account = accountRepository.findByAccountNumber(request.accountNumber())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.USER_NOT_FOUND)));

        account.registeredBy(user);

        return AccountRegistrationResponse.from(account);
    }

    public AccountCreateResponse save(AccountCreateRequest request) {

        Account account = accountRepository.save(request.toEntity());

        return AccountCreateResponse.from(account);
    }

    @Transactional(readOnly = true)
    public AccountReadResponse getAccount(Long accountId){

        Account account = accountRepository.getByIdFetchJoin(accountId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        return AccountReadResponse.from(
                account.getId(),
                account.getOwner(),
                account.getType(),
                account.getAccountNumber(),
                account.getAccountName(),
                account.getProductName(),
                account.getBalance(),
                account.getIsMainAccount()
        );
    }
}
