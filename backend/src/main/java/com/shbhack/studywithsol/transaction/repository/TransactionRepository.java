package com.shbhack.studywithsol.transaction.repository;

import com.shbhack.studywithsol.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, CustomTransactionRepository {
}
