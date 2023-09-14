package com.shbhack.studywithsol.timer.repository;

import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    List<Timer> findAllByChildrenIdAndAndStudyDate(Long childrenId, LocalDate studyDate);
}
