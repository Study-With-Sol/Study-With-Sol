package com.shbhack.studywithsol.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class StudyDto {

    @Getter
    @Builder
    public static class StudyRequestDto{
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate deadline;

        @NotNull
        private Long childrenId;

        @NotNull
        private Long parentId;
    }

    @Getter
    @Builder
    public static class StudyResponseDto{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate date;
        private String content;
        private String payMoney;
        private Boolean isLongGoal;

        public static StudyResponseDto from(LocalDate date, String content, String payMoney, Boolean isLongGoal){
            return StudyResponseDto.builder()
                    .date(date)
                    .content(content)
                    .payMoney(payMoney)
                    .isLongGoal(isLongGoal)
                    .build();
        }
    }
}
