package com.shbhack.studywithsol.timer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shbhack.studywithsol.goal.domain.WantPay;
import com.shbhack.studywithsol.timer.domain.Timer;
import com.shbhack.studywithsol.timer.domain.TimerState;
import com.shbhack.studywithsol.user.domain.Connection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)

public class TimerDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class StudyWithTimerReqDto{
        @NotNull
        private Long parentId;

        @NotNull
        private String content;

        @NotNull
        private String time;

        private WantPay wantPay;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ListReqDto{
        @NotNull
        private Long childrenId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ParentReqDto{
        @NotNull
        private Long money;
    }

    @Getter
    @Builder
    public static class ResponseDto{
        private Long money;
        private TimerState payState;

        public static ResponseDto of(Long money, TimerState payState){
            return ResponseDto.builder()
                    .money(money)
                    .payState(payState)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ListResDto{
        private String content;
        private String time;
        private LocalDate studyDate;
        private WantPay wantPay;

        public static ListResDto from (Timer timer){
            return ListResDto.builder()
                    .content(timer.getContent())
                    .time(timer.getTime())
                    .studyDate(timer.getStudyDate())
                    .wantPay(timer.getWantPay())
                    .build();
        }
    }
}
