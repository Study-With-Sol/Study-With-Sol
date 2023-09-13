package com.shbhack.studywithsol.study.repository;

import com.shbhack.studywithsol.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {
    void deleteAllByChildrenIdAndParentIdAndDeadline(Long childrenId, Long parentId, LocalDate deadline);

    List<Study> findAllByChildrenId(Long childrenId);

    List<Study> findAllByChildrenIdAndParentId(Long childrenId, Long parentId);

    List<Study> findAllByChildrenIdAndParentIdAndDeadline(Long childrenId, Long parentId, LocalDate deadline);
}
