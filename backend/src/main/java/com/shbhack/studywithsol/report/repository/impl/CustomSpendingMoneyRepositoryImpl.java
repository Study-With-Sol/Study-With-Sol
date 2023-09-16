package com.shbhack.studywithsol.report.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shbhack.studywithsol.report.domain.SpendingReport;
import com.shbhack.studywithsol.report.dto.response.SpendingReportResponse;
import com.shbhack.studywithsol.report.repository.CustomSpendingMoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.shbhack.studywithsol.report.domain.QSpendingReport.spendingReport;
import static com.shbhack.studywithsol.user.domain.QConnection.connection;

@Repository
@RequiredArgsConstructor
public class CustomSpendingMoneyRepositoryImpl implements CustomSpendingMoneyRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SpendingReportResponse> getSpendingReport(Long connectionId, Integer year, Integer month) {

        List<SpendingReport> spendingReports = queryFactory.select(spendingReport)
                .from(spendingReport)
                .leftJoin(spendingReport.connection, connection)
                .fetchJoin()
                .where(spendingReport.year.eq(year),
                        spendingReport.month.eq(month),
                        spendingReport.connection.connectionId.eq(connectionId))
                .orderBy(spendingReport.percent.desc())
                .fetch();

        List<SpendingReportResponse> content = new ArrayList<>();
        for (SpendingReport spendingReport: spendingReports) {
            SpendingReportResponse item = SpendingReportResponse.of(spendingReport);
            content.add(item);
        }

        return content;

    }

}
