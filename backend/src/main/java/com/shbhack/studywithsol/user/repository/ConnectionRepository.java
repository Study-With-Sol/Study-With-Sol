package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
