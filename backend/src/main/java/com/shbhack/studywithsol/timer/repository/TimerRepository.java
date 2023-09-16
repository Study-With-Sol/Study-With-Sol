package com.shbhack.studywithsol.timer.repository;

import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.dto.TimerDto;
import com.shbhack.studywithsol.user.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    List<Timer> findAllByChildrenIdAndStudyDate(Long childrenId, LocalDate studyDate);

    List<Timer> findAllByParentIdAndChildrenIdAndPayStateIsFalse(Long parentId, Long childrenId);
}
