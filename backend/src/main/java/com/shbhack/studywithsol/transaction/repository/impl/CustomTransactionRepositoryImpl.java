package com.shbhack.studywithsol.transaction.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.transaction.domain.Transaction;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import com.shbhack.studywithsol.transaction.repository.CustomTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.shbhack.studywithsol.account.domain.QAccount.account;
import static com.shbhack.studywithsol.transaction.domain.QTransaction.transaction;

@Repository
@RequiredArgsConstructor
public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<TransactionListReadResponse> getTransactionList(Long accountId, Pageable pageable) {

        List<Transaction> transactions = queryFactory.select(transaction)
                .from(transaction)
                .leftJoin(transaction.account, account)
                .fetchJoin()
                .where(transaction.account.id.eq(accountId))
                .orderBy(transaction.useDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<TransactionListReadResponse> content = new ArrayList<>();
        for (Transaction transaction: transactions) {
            TransactionListReadResponse item = TransactionListReadResponse.of(transaction);
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
