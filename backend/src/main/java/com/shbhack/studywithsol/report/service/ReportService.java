package com.shbhack.studywithsol.report.service;

import com.shbhack.studywithsol.report.dto.StudyReportDto;

import java.util.List;

public interface ReportService {
    List<StudyReportDto.ResponseDto> studyReportForParent(Long parentId, StudyReportDto.RequestDto requestDto);
    List<StudyReportDto.ResponseDto> studyReportForChild(Long childrenId, StudyReportDto.RequestDto requestDto);
}
