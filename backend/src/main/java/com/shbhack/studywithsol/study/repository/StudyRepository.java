package com.shbhack.studywithsol.study.repository;

import com.shbhack.studywithsol.study.domain.Study;
import com.shbhack.studywithsol.user.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

    List<Study> findAllByChildrenIdAndDeadline(Long childrenId, LocalDate deadline);

    List<Study> findAllByChildrenIdAndParentIdAndDeadline(Long childrenId, Long parentId, LocalDate deadline);
    List<Study> findAllByParentId(Long parentId);
}
