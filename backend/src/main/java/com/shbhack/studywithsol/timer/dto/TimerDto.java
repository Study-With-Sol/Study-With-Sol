package com.shbhack.studywithsol.timer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shbhack.studywithsol.timer.domain.TimerState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)

public class TimerDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class StudyWithTimerReqDto{
        @NotNull
        private Long childrenId;

        @NotNull
        private Long parentId;

        @NotNull
        private String content;

        @NotNull
        private String time;

        private String imageUrl;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ParentReqDto{
        @NotNull
        private int money;
    }

    @Getter
    @Builder
    public static class ResponseDto{
        private int money;
        private TimerState payState;

        public static ResponseDto of(int money, TimerState payState){
            return ResponseDto.builder()
                    .money(money)
                    .payState(payState)
                    .build();
        }
    }
}
