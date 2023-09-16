package com.shbhack.studywithsol.transaction.controller;

import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.request.TransactionListReadRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value ="거래")
    @PostMapping
    public BaseResponseDto<TransactionCreateResponse> save(@RequestBody @Valid TransactionCreateRequest request) {
        return BaseResponseDto.ok(transactionService.save(request));
    }

    @ApiOperation(value ="거래 내역 조회")
    @PostMapping("/list")
    public BaseResponseDto<Slice<TransactionListReadResponse>> getTransactionList(@RequestBody @Valid TransactionListReadRequest request,
                                                                                  Pageable pageable) {
        return BaseResponseDto.ok(transactionService.getTransactionList(request.accountId(), pageable));
    }

}
