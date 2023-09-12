package com.shbhack.studywithsol.study.repo;

import com.shbhack.studywithsol.study.dto.StudyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyListRepo extends JpaRepository<StudyList, Long> {
}
