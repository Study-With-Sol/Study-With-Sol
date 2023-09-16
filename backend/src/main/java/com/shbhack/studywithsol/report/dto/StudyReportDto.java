package com.shbhack.studywithsol.report.dto;

import com.shbhack.studywithsol.user.domain.Connection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class StudyReportDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestDto{
        @NotNull
        private LocalDate startDate;
        @NotNull
        private LocalDate endDate;

        private Long childrenId;
    }

    @Getter
    @Builder
    public static class ResponseDto{
        private int settingStudy;
        private int completeStudy;
        private int allowance;
        private String time;

        public static ResponseDto from(
                int settingStudy, int completeStudy, int allowance, String time){
            return ResponseDto.builder()
                    .settingStudy(settingStudy)
                    .completeStudy(completeStudy)
                    .allowance(allowance)
                    .time(time)
                    .build();
        }
    }
}
