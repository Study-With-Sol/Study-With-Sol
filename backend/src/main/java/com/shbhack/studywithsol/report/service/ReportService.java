package com.shbhack.studywithsol.report.service;

import com.shbhack.studywithsol.report.dto.StudyReportDto;

import java.util.List;

public interface ReportService {
    List<StudyReportDto.ResponseDto> studyReport(Boolean isChild, StudyReportDto.RequestDto requestDto);
}
