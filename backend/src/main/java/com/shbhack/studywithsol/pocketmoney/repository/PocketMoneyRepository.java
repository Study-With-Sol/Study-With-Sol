package com.shbhack.studywithsol.pocketmoney.repository;

import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketMoneyRepository extends JpaRepository<PocketMoney, Long>, CustomPocketMoneyRepository {
}
