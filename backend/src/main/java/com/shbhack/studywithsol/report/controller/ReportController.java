package com.shbhack.studywithsol.report.controller;

import com.shbhack.studywithsol.report.dto.StudyReportDto;
import com.shbhack.studywithsol.report.dto.request.SpendingReportRequest;
import com.shbhack.studywithsol.report.dto.response.SpendingReportResponse;
import com.shbhack.studywithsol.report.service.ReportService;
import com.shbhack.studywithsol.report.service.SpendingReportService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    private final SpendingReportService spendingReportService;

    @ApiOperation(value ="자녀가 본인의 학습 레포트 조회")
    @PostMapping("/study/child")
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForChild(Authentication authentication, @RequestBody StudyReportDto.RequestDto requestDto){
        Long childrenId = Long.valueOf(authentication.getName());
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReportForChild(childrenId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ApiOperation(value ="부모가 자녀의 학습 레포트 조회")
    @PostMapping("/study")
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForParent(Authentication authentication, @RequestBody StudyReportDto.RequestDto requestDto){
        Long parentId = Long.valueOf(authentication.getName());
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReportForParent(parentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ApiOperation(value ="용돈 지출 레포트 조회")
    @PostMapping("/spending")
    public BaseResponseDto<List<SpendingReportResponse>> getSpendingReport(@RequestBody @Valid SpendingReportRequest request) {
        return BaseResponseDto.ok(spendingReportService.getSpendingReport(request));
    }
}
