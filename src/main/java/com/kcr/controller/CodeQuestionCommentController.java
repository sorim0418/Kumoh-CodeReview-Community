package com.kcr.controller;

import com.kcr.domain.dto.codequestion.CodeQuestionRequestDTO;
import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentRequestDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentRequestDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.Question;
import com.kcr.repository.CodeQuestionCommentRepository;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.service.CodeQuestionCommentService;
import com.kcr.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CodeQuestionCommentController {
    @Autowired
    private CodeQuestionCommentService codeQuestionCommentService;
    @Autowired
    private CodeQuestionRepository codeQuestionRepository;
    @Autowired
    private CodeQuestionCommentRepository codeQuestionCommentRepository;

    //댓글등록
    @PostMapping("/codequestion/{id}/comment")
    public ResponseEntity<Long> commentSave(@PathVariable Long id, @RequestBody CodeQuestionCommentRequestDTO codeQuestionCommentRequestDTO) {
        CodeQuestion codeQuestion = codeQuestionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        return ResponseEntity.ok(codeQuestionCommentService.commentSave(id, codeQuestionCommentRequestDTO));
    }

    //댓글수정
    @PutMapping({"/codequestion/{codequestionid}/comments/{Id}"})
    public ResponseEntity<Long> update(@PathVariable Long codequestionId, @PathVariable Long id, @RequestBody CodeQuestionRequestDTO codeQuestionRequestDTO) {
        codeQuestionCommentService.updateComment(codequestionId, id, codeQuestionRequestDTO);
        return ResponseEntity.ok(id);
    }

    //댓글 삭제
    @DeleteMapping("/codequestion/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        codeQuestionCommentService.delete(id);
    }
}
