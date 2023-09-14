package com.shbhack.studywithsol.user.repository.impl;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.user.domain.Message;
import com.shbhack.studywithsol.user.repository.CustomMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.beans.Expression;

import static com.shbhack.studywithsol.user.domain.QMessage.message;

@Repository
@RequiredArgsConstructor
public class CustomMessageRepositoryImpl implements CustomMessageRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Message getRandomMessage() {
        return queryFactory.selectFrom(message)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .fetchFirst();

    }
}
