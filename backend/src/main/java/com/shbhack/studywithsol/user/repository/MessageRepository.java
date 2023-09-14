package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> , CustomMessageRepository{

}
