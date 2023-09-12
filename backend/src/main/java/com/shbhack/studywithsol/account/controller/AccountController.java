package com.shbhack.studywithsol.account.controller;

import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountRegistrationRequest;
import com.shbhack.studywithsol.account.dto.response.AccountCreateResponse;
import com.shbhack.studywithsol.account.dto.response.AccountReadResponse;
import com.shbhack.studywithsol.account.dto.response.AccountRegistrationResponse;
import com.shbhack.studywithsol.account.service.AccountService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    //User 설정 후 변경 필요
    @PostMapping
    public BaseResponseDto<AccountRegistrationResponse> registration(@RequestBody @Valid AccountRegistrationRequest request) {

        return BaseResponseDto.ok(accountService.registration(request, 1L));
    }

    @GetMapping("/{accountId}")
    public BaseResponseDto<AccountReadResponse> getAccount(@PathVariable Long accountId) {
        return BaseResponseDto.ok(accountService.getAccount(accountId));
    }

    @PostMapping("/creation")
    public BaseResponseDto<AccountCreateResponse> save(@RequestBody @Valid AccountCreateRequest request) {
        return BaseResponseDto.ok(accountService.save(request));
    }

}
