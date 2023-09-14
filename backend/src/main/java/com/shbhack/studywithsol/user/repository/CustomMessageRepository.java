package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomMessageRepository {
    Message getRandomMessage();

}
