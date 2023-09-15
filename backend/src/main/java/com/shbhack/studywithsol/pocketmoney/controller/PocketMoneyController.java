package com.shbhack.studywithsol.pocketmoney.controller;

import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyCreateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyReadRequest;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyUpdateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyCreateResponse;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyReadResponse;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyUpdateResponse;
import com.shbhack.studywithsol.pocketmoney.service.PocketMoneyService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public BaseResponseDto<PocketMoneyCreateResponse> savePocketMoney(@RequestBody @Valid PocketMoneyCreateRequest request) {
        return BaseResponseDto.ok(pocketMoneyService.save(request));
    }

    @ApiOperation(value ="용돈 조회")
    @GetMapping
    public BaseResponseDto<PocketMoneyReadResponse> getPocketMoney(@RequestBody @Valid PocketMoneyReadRequest request) {
        return BaseResponseDto.ok(pocketMoneyService.getPocketMoney(request));
    }

    @ApiOperation(value ="용돈 수정")
    @PutMapping
    public BaseResponseDto<PocketMoneyUpdateResponse> updatePocketMoney(@RequestBody @Valid PocketMoneyUpdateRequest request) {
        return BaseResponseDto.ok(pocketMoneyService.update(request));
    }

}
