package com.shbhack.studywithsol.account.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.repository.CustomAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.shbhack.studywithsol.account.domain.QAccount.account;
import static com.shbhack.studywithsol.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Account> getByIdFetchJoin(Long accountId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(account)
                        .leftJoin(account.user, user).fetchJoin()
                        .where(account.id.eq(accountId))
                        .fetchOne()
        );
    }

}
