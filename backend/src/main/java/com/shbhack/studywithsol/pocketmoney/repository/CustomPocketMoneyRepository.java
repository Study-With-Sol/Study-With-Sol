package com.shbhack.studywithsol.pocketmoney.repository;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;

import java.util.Optional;

public interface CustomPocketMoneyRepository {

    Optional<PocketMoney> getByIdFetchJoin(Long pocketMoneyId);

}
