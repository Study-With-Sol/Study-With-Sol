package com.shbhack.studywithsol.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shbhack.studywithsol.study.domain.Study;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudyRequestDto{
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate deadline;

        @NotNull
        private Long childrenId;

        @NotNull
        private Long parentId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class RegisterStudyListReqDto{
        @NotNull
        private Long childrenId;

        @NotNull
        private Long parentId;

        @NotNull
        private String content;

        @NotNull
        private int payMoney;

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate deadline;
    }

    @Getter
    @Builder
    public static class StudyResponseDto{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate deadline;
        private String content;
        private int payMoney;

        public static StudyResponseDto from(Study study){
            return StudyResponseDto.builder()
                    .deadline(study.getDeadline())
                    .content(study.getContent())
                    .payMoney(study.getPayMoney())
                    .build();
        }
    }
}
