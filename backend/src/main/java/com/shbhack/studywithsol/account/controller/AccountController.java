package com.shbhack.studywithsol.account.controller;

import com.shbhack.studywithsol.account.dto.request.AccountCreateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountMainUpdateRequest;
import com.shbhack.studywithsol.account.dto.request.AccountReadRequest;
import com.shbhack.studywithsol.account.dto.request.AccountRegistrationRequest;
import com.shbhack.studywithsol.account.dto.request.AccountTerminationRequest;
import com.shbhack.studywithsol.account.dto.response.AccountCreateResponse;
import com.shbhack.studywithsol.account.dto.response.AccountMainUpdateResponse;
import com.shbhack.studywithsol.account.dto.response.AccountMainBalanceReadResponse;
import com.shbhack.studywithsol.account.dto.response.AccountListReadResponse;
import com.shbhack.studywithsol.account.dto.response.AccountReadResponse;
import com.shbhack.studywithsol.account.dto.response.AccountRegistrationResponse;
import com.shbhack.studywithsol.account.dto.response.AccountTerminationResponse;
import com.shbhack.studywithsol.account.service.AccountService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @ApiOperation(value ="게좌 등록")
    @PostMapping
    public BaseResponseDto<AccountRegistrationResponse> registration(@RequestBody @Valid AccountRegistrationRequest request,
                                                                     Authentication authentication) {
        return BaseResponseDto.ok(accountService.registration(request, Long.valueOf(authentication.getName())));
    }

    @ApiOperation(value ="주계좌 수정")
    @PatchMapping
    public BaseResponseDto<AccountMainUpdateResponse> changeMainAccount(@RequestBody @Valid AccountMainUpdateRequest request,
                                                                        Authentication authentication) {
        return BaseResponseDto.ok(accountService.changeMainAccount(request, Long.valueOf(authentication.getName())));
    }

    @ApiOperation(value ="계좌 등록 해지")
    @DeleteMapping
    public BaseResponseDto<AccountTerminationResponse> termination(@RequestBody @Valid AccountTerminationRequest request,
                                                                   Authentication authentication) {
        return BaseResponseDto.ok(accountService.termination(request, Long.valueOf(authentication.getName())));
    }

    @ApiOperation(value ="주계좌 잔액 조회")
    @GetMapping("/balance")
    public BaseResponseDto<AccountMainBalanceReadResponse> getMainAccountBalance(Authentication authentication) {
        return BaseResponseDto.ok(accountService.getMainAccountBalance(Long.valueOf(authentication.getName())));
    }

    @ApiOperation(value ="계좌 조회")
    @PostMapping("/inquiry")
    public BaseResponseDto<AccountReadResponse> getAccount(@RequestBody @Valid AccountReadRequest request) {
        return BaseResponseDto.ok(accountService.getAccount(request));
    }

    @ApiOperation(value ="전체 계좌 조회")
    @GetMapping("/list")
    public BaseResponseDto<Slice<AccountListReadResponse>> getAccountList(Pageable pageable,
                                                                          Authentication authentication) {
        return BaseResponseDto.ok(accountService.getAccountList(Long.valueOf(authentication.getName()), pageable));
    }

    @ApiOperation(value ="계좌 생성")
    @PostMapping("/creation")
    public BaseResponseDto<AccountCreateResponse> save(@RequestBody @Valid AccountCreateRequest request) {
        return BaseResponseDto.ok(accountService.save(request));
    }

}
