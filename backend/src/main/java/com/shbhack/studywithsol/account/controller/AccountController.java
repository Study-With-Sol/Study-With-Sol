package com.shbhack.studywithsol.account.controller;

import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.response.AccountCreateResponse;
import com.shbhack.studywithsol.account.service.AccountService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/creation")
    public BaseResponseDto<AccountCreateResponse> save(@RequestBody @Valid AccountCreateRequest request) {
        return BaseResponseDto.ok(accountService.save(request));
    }
}
