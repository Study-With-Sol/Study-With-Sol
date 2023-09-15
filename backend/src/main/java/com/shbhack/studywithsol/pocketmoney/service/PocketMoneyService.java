package com.shbhack.studywithsol.pocketmoney.service;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyCreateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyReadRequest;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyCreateResponse;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyReadResponse;
import com.shbhack.studywithsol.pocketmoney.repository.PocketMoneyRepository;
import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PocketMoneyService {

    private PocketMoneyRepository pocketMoneyRepository;
    private ConnectionRepository connectionRepository;

    public PocketMoneyCreateResponse save(PocketMoneyCreateRequest request) {

        Connection connection = connectionRepository.findById(request.connectionId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.CONNECTION_NOT_FOUND)));

        PocketMoney pocketMoney = pocketMoneyRepository.save(request.toEntity());

        pocketMoney.connectionBy(connection);

        return PocketMoneyCreateResponse.from(pocketMoney);
    }

    @Transactional(readOnly = true)
    public PocketMoneyReadResponse getPocketMoney(PocketMoneyReadRequest request){

        PocketMoney pocketMoney = pocketMoneyRepository.getByConnectionId(request.connectionId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.POCKET_MONEY_NOT_FOUND)));

        return PocketMoneyReadResponse.from(pocketMoney);
    }

}
