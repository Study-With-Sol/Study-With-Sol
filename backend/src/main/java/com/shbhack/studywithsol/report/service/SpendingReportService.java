package com.shbhack.studywithsol.report.service;

import com.shbhack.studywithsol.report.dto.request.SpendingReportRequest;
import com.shbhack.studywithsol.report.dto.response.SpendingReportResponse;
import com.shbhack.studywithsol.report.repository.SpendingMoneyRepository;
import com.shbhack.studywithsol.transaction.dto.response.TransactionListReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SpendingReportService {

    private final SpendingMoneyRepository spendingMoneyRepository;

    @Transactional(readOnly = true)
    public List<SpendingReportResponse> getSpendingReport(SpendingReportRequest request){

        List<SpendingReportResponse> lists = spendingMoneyRepository
                .getSpendingReport(request.connectionId(), request.year(), request.month());

        return lists;
    }
}
