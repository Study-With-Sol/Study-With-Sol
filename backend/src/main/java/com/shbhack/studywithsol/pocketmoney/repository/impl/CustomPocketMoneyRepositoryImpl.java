package com.shbhack.studywithsol.pocketmoney.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.pocketmoney.domain.PocketMoney;
import com.shbhack.studywithsol.pocketmoney.repository.CustomPocketMoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.shbhack.studywithsol.pocketmoney.domain.QPocketMoney.pocketMoney;
import static com.shbhack.studywithsol.user.domain.QConnection.connection;

@Repository
@RequiredArgsConstructor
public class CustomPocketMoneyRepositoryImpl implements CustomPocketMoneyRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PocketMoney> getByIdFetchJoin(Long pocketMoneyId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(pocketMoney)
                        .leftJoin(pocketMoney.connection, connection).fetchJoin()
                        .where(pocketMoney.id.eq(pocketMoneyId))
                        .fetchOne()
        );
    }

    @Override
    public Optional<PocketMoney> getByConnectionId(Long connectionId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(pocketMoney)
                        .leftJoin(pocketMoney.connection, connection).fetchJoin()
                        .where(connection.connectionId.eq(connectionId))
                        .fetchOne()
        );
    }

}
