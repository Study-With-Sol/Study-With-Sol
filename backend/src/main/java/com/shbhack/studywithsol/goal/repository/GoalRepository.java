package com.shbhack.studywithsol.goal.repository;

import com.shbhack.studywithsol.goal.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<Goal> findByGoalIdAndPayIsFalse(Long goalId);

    Optional<Goal> findByParentIdAndChildrenIdAndPayIsFalse(Long parentId, Long childrenId);

    List<Goal> findAllByChildrenId(Long childrenId);
}
