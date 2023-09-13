package com.shbhack.studywithsol.account.service;

import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountMainUpdateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountRegistrationRequest;
import com.shbhack.studywithsol.account.dto.request.AccountTerminationRequest;
import com.shbhack.studywithsol.account.dto.response.*;
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

        if(user.getMainAccount() == null){
            user.setMainAccount(account);
            account.setIsMainAccount(true);
        }

        return AccountRegistrationResponse.from(account);
    }

    public AccountMainUpdateResponse changeMainAccount(AccountMainUpdateRequest request, Long userId) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.USER_NOT_FOUND)));

        Account mainAccount = accountRepository.findById(user.getMainAccount().getId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        user.setMainAccount(account);

        mainAccount.setIsMainAccount(false);
        account.setIsMainAccount(true);

        return AccountMainUpdateResponse.from(account);
    }

    public AccountTerminationResponse termination(AccountTerminationRequest request, Long userId) {

        Account account = accountRepository.findById(request.id())
                .orElseThrow(() -> new BusinessException((ErrorMessage.ACCOUNT_NOT_FOUND)));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.USER_NOT_FOUND)));

        if(account.getUser().equals(user)){
            account.terminatedBy();
        }

        return AccountTerminationResponse.from(account);
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
