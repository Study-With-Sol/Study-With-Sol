package com.shbhack.studywithsol.pocketmoney.service;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyCreateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyReadRequest;
import com.shbhack.studywithsol.pocketmoney.dto.request.PocketMoneyUpdateRequest;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyCreateResponse;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyReadResponse;
import com.shbhack.studywithsol.pocketmoney.dto.response.PocketMoneyUpdateResponse;
import com.shbhack.studywithsol.pocketmoney.repository.PocketMoneyRepository;
import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PocketMoneyService {

    private final PocketMoneyRepository pocketMoneyRepository;
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;


    public PocketMoneyCreateResponse save(PocketMoneyCreateRequest request, Long parentId) {

        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리
        User child = userRepository.findById(request.childId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리

        Connection connection = connectionRepository.findByParentAndChildren(parent, child);
        if(connection == null) {
            throw new BusinessException(ErrorMessage.CONNECTION_NOT_FOUND);
        }

        PocketMoney pocketMoney = pocketMoneyRepository.save(request.toEntity());

        pocketMoney.connectionBy(connection);

        return PocketMoneyCreateResponse.from(pocketMoney);
    }

    @Transactional(readOnly = true)
    public PocketMoneyReadResponse getPocketMoney(PocketMoneyReadRequest request, Long parentId){

        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리
        User child = userRepository.findById(request.childId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리

        Connection connection = connectionRepository.findByParentAndChildren(parent, child);
        if(connection == null) {
            throw new BusinessException(ErrorMessage.CONNECTION_NOT_FOUND);
        }

        PocketMoney pocketMoney = pocketMoneyRepository.getByConnectionId(connection.getConnectionId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.POCKET_MONEY_NOT_FOUND)));

        return PocketMoneyReadResponse.from(pocketMoney);
    }

    public PocketMoneyUpdateResponse update(PocketMoneyUpdateRequest request) {

        PocketMoney pocketMoney = pocketMoneyRepository.getByConnectionId(request.pocketMoneyId())
                .orElseThrow(() -> new BusinessException((ErrorMessage.POCKET_MONEY_NOT_FOUND)));

        pocketMoney.update(request.toDto());

        return new PocketMoneyUpdateResponse(
                pocketMoney.getId(),
                pocketMoney.getConnection().getConnectionId(),
                pocketMoney.getAmount(),
                pocketMoney.getPaymentDate(),
                pocketMoney.getPaymentStatus()
        );
    }

}
