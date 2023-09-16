package com.shbhack.studywithsol.report.dto.request;

import javax.validation.constraints.NotNull;

public record SpendingReportRequest(

        /**
         * connectionId : 관계 pk
         * year : 년
         * month : 월
         */

        @NotNull
        Long connectionId,

        @NotNull
        Integer year,

        @NotNull
        Integer month

) {
}
