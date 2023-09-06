package com.shbhack.studywithsol.account.repository;

import com.shbhack.studywithsol.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
