package com.shbhack.studywithsol.report.controller;

import com.shbhack.studywithsol.report.dto.StudyReportDto;
import com.shbhack.studywithsol.report.dto.request.SpendingReportRequest;
import com.shbhack.studywithsol.report.dto.response.SpendingReportResponse;
import com.shbhack.studywithsol.report.service.ReportService;
import com.shbhack.studywithsol.report.service.SpendingReportService;
import com.shbhack.studywithsol.utils.dto.response.BaseResponseDto;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    private final SpendingReportService spendingReportService;

    @ApiOperation(value ="학습 레포트 조회")
    @PostMapping("/study/child")
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForChild(@RequestBody StudyReportDto.RequestDto requestDto){
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReport(true, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ApiOperation(value ="자녀 학습 레포트 조회")
    @PostMapping("/study")
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForParent(@RequestBody StudyReportDto.RequestDto requestDto){
        if(requestDto.getParentId()==null){
            throw new BusinessException(ErrorMessage.NO_ENOUGH_INFORMATION);
        }
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReport(false, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @ApiOperation(value ="용돈 지출 레포트 조회")
    @PostMapping("/spending")
    public BaseResponseDto<List<SpendingReportResponse>> getSpendingReport(@RequestBody @Valid SpendingReportRequest request) {
        return BaseResponseDto.ok(spendingReportService.getSpendingReport(request));
    }
}
