package com.shbhack.studywithsol.goal.service;

import com.shbhack.studywithsol.goal.dto.GoalDto;

import java.util.List;

public interface GoalService {
    String registerLongGoal(Long parentId, GoalDto.GoalReqDto goalReqDto);

    String deleteLongGoal(Long goalId);

    List<GoalDto.GoalRespDto> getLongGoal(Long childrenId);

    GoalDto.GoalRespDto getLongGoalParent(Long parentId, Long childrenId);
}
