package com.kcr.controller;

import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentResponseDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.report.ReportRequestDTO;
import com.kcr.domain.dto.report.ReportResponseDTO;
import com.kcr.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private ReportService reportService;
    //작성자신고
    @PostMapping("/reportbywriter")
    public ResponseEntity<Long> saveReportByWriter(@RequestBody ReportRequestDTO reportRequestDTO) {
        Long reportID= reportService.saveWriterReport(reportRequestDTO);
        System.out.println("작성자 신고 등록 "+reportID);
        return ResponseEntity.ok(reportService.saveWriterReport(reportRequestDTO));
    }
    //댓글 신고
    @PostMapping("/reportbycomment")
    public ResponseEntity<Long> saveReportByComment(@RequestBody ReportRequestDTO reportRequestDTO) {
        return ResponseEntity.ok(reportService.saveCommentReport(reportRequestDTO));
    }

    //게시글 댓글 신고
    @PostMapping("/reportbypost")
    public ResponseEntity<Long> saveReportByPost(@RequestBody ReportRequestDTO reportRequestDTO) {
        return ResponseEntity.ok(reportService.savePostReport(reportRequestDTO));
    }

    //신고 전체 조회(관리자)
    @GetMapping("/reportall")
    public ResponseEntity<List<ReportResponseDTO>> getChildComments() {
        List<ReportResponseDTO> reportList = reportService.findAllReport();
        return ResponseEntity.ok(reportList);
    }

}
