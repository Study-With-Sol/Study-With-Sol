package com.shbhack.studywithsol.report.dto.request;

import javax.validation.constraints.NotNull;

public record SpendingReportRequest(

        /**
         * childId : 자녀 pk
         * year : 년
         * month : 월
         */

        @NotNull
        Long childId,

        @NotNull
        Integer year,

        @NotNull
        Integer month

) {
}
