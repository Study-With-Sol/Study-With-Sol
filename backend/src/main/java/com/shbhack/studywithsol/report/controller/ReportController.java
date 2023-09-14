package com.shbhack.studywithsol.report.controller;

import com.shbhack.studywithsol.report.dto.StudyReportDto;
import com.shbhack.studywithsol.report.service.ReportService;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study-report")
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/child")
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForChild(@RequestBody StudyReportDto.RequestDto requestDto){
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReport(true, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<List<StudyReportDto.ResponseDto>> studyReportForParent(@RequestBody StudyReportDto.RequestDto requestDto){
        if(requestDto.getParentId()==null){
            throw new BusinessException(ErrorMessage.NO_ENOUGH_INFORMATION);
        }
        List<StudyReportDto.ResponseDto> responseDto = reportService.studyReport(false, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
