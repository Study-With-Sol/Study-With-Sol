package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
