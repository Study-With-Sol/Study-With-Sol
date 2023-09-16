package com.shbhack.studywithsol.report.repository;

import com.shbhack.studywithsol.report.dto.response.SpendingReportResponse;

import java.util.List;

public interface CustomSpendingMoneyRepository {

    List<SpendingReportResponse> getSpendingReport(Long connectionId, Integer year, Integer month);

}
