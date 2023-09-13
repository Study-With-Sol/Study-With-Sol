package com.shbhack.studywithsol.timer.repository;

import com.shbhack.studywithsol.timer.domain.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<Timer, Long> {
}
