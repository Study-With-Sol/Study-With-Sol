package com.shbhack.studywithsol.pocketmoney.controller;

import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyCreateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyCreateResponse;
import com.shbhack.studywithsol.pocketmoney.service.PocketMoneyService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-money")
public class PocketMoneyController {

    private final PocketMoneyService pocketMoneyService;

    @ApiOperation(value ="용돈 저장")
    @PostMapping
    public BaseResponseDto<PocketMoneyCreateResponse> save(@RequestBody @Valid PocketMoneyCreateRequest request) {
        return BaseResponseDto.ok(pocketMoneyService.save(request));
    }

}
