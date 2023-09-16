package com.shbhack.studywithsol.report.repository;

import com.shbhack.studywithsol.report.domain.SpendingReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingMoneyRepository extends JpaRepository<SpendingReport, Long>, CustomSpendingMoneyRepository {
}
