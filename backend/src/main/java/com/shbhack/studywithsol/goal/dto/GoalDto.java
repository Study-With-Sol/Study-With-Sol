package com.shbhack.studywithsol.goal.dto;

import com.shbhack.studywithsol.goal.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class GoalDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoalReqDto{
        @NotNull
        private String content;

        @NotNull
        private Long childrenId;

        @NotNull
        private LocalDate deadline;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetGoalReqDto{
        @NotNull
        private Long childrenId;
    }


    @Getter
    @Builder
    public static class GoalRespDto{
        private String content;
        private Long money;
        private LocalDate deadline;

        public static GoalRespDto from(Goal goal) {
            return GoalRespDto.builder()
                    .content(goal.getContent())
                    .money(goal.getMoney())
                    .deadline(goal.getDeadline())
                    .build();
        }
    }
}
