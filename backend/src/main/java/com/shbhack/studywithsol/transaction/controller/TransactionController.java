package com.shbhack.studywithsol.transaction.controller;

import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.dto.response.TransactionCreateResponse;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public BaseResponseDto<TransactionCreateResponse> save(@RequestBody @Valid TransactionCreateRequest request) {
        return BaseResponseDto.ok(transactionService.save(request));
    }

}
