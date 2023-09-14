package com.shbhack.studywithsol.account.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.account.domain.Account;
import com.shbhack.studywithsol.account.dto.response.AccountListReadResponse;
import com.shbhack.studywithsol.account.repository.CustomAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(account)
                        .where(account.accountNumber.eq(accountNumber))
                        .fetchOne()
        );
    }

    @Override
    public Slice<AccountListReadResponse> getAccountList(Long userId, Pageable pageable) {

        List<Account> accounts = queryFactory.select(account)
                .from(account)
                .leftJoin(account.user, user)
                .fetchJoin()
                .where(account.user.userId.eq(userId))
                .orderBy(account.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<AccountListReadResponse> content = new ArrayList<>();
        for (Account account: accounts) {
            AccountListReadResponse item = AccountListReadResponse.of(account);
            content.add(item);
        }

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

}
