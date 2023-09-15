package com.shbhack.studywithsol.report.domain;

public record SpendingReport(

        /**
         * content : 지출 내용(카테고리)
         * percent : 퍼센트
         * amount : 금액
         */

        String content,

        Double percent,

        Long amount
) {
}
