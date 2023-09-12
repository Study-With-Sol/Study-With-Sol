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
import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyDto {

    // 학습 조회, 삭제에 사용 되는 Request DTO
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

        private Long parentId;
    }

    // 학습 등록 시 오는 Request DTO
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

        @NotNull
        private Boolean isLongGoal;
    }

    //학습 조회 시 반환하기 위한 Response DTO
    @Getter
    @Builder
    public static class StudyResponseDto{
        private List<Study> studyList;
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//        private LocalDate deadline;
//        private String content;
//        private int payMoney;

        public static StudyResponseDto from(List<Study> study){
            return StudyResponseDto.builder()
                    .studyList(study)
                    .build();
        }
    }
}
