package com.kcr.domain.dto.report;

import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.domain.entity.Report;
import com.kcr.domain.type.ReportType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ReportResponseDTO {
    private Long reportId;
    private String content;
    private ReportType reportType;
    private String title;

    /* Entity -> DTO */
    @Builder
    public ReportResponseDTO(Long id, String title, String content, ReportType reportType) {
        this.reportId =id;
        this.title = title;
        this.content = content;
        this.reportType=reportType;

    }
    public ReportResponseDTO(Report report) {
        this.reportId = report.getId();
        this.content=report.getContent();
        this.reportType=report.getReportType();
        this.title=report.getTitle();
    }

    public static ReportResponseDTO toReportDTO(Report report) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setReportId(report.getId());
        dto.setTitle(report.getTitle());
        dto.setReportType(report.getReportType());
        dto.setContent(report.getContent());
        // 추가적인 필드가 있다면 여기에 매핑 로직을 추가하세요.
        return dto;
    }

}
