package com.shbhack.studywithsol.report.dto.response;

import com.shbhack.studywithsol.report.domain.SpendingReport;

public record SpendingReportResponse(

        String content,

        Double percent,

        Long amount

) {

    public static SpendingReportResponse of(SpendingReport spendingReport) {
        return new SpendingReportResponse(
                spendingReport.getContent(),
                spendingReport.getPercent(),
                spendingReport.getAmount()
        );
    }
}
