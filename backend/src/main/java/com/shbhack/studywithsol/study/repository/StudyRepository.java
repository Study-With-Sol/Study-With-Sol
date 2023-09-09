package com.shbhack.studywithsol.study.repository;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {
    void deleteAllByChildrenIdAndParentIdAndDeadline(Long childrenId, Long parentId, LocalDate deadline);
}
