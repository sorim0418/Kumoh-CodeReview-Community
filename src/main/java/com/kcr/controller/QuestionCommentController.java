package com.kcr.controller;

import com.kcr.domain.dto.questioncomment.QuestionCommentRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.Question;
import com.kcr.repository.QuestionCommentRepository;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionCommentController {
    @Autowired
    private QuestionCommentService questionCommentService;
    @Autowired
    private QuestionCommentRepository questionCommentRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public int write(QuestionCommentRequestDTO questionCommentRequestDTO) {
        return 0;
    }
    //댓글 수정
    @PutMapping("/questions/{questionId}/comments/{id}")
    public ResponseEntity<Long> update(@PathVariable Long questionId,
                                       @PathVariable Long id,
                                       @RequestBody QuestionCommentRequestDTO requestDTO) {
        questionCommentService.updateComment(questionId, id, requestDTO);
        return ResponseEntity.ok(id);
    }

    // 댓글 삭제
    @DeleteMapping("/questioncomment/{id}/delete")
    public void delete(@PathVariable("id") Long id) {
        questionCommentService.delete(id);
    }


    //댓글등록
    @PostMapping("/question/{id}/comment")
    public ResponseEntity<Long> commentSave(@PathVariable Long id, @RequestBody QuestionCommentRequestDTO questionCommentRequestDTO) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        return ResponseEntity.ok(questionCommentService.commentSave(id, questionCommentRequestDTO));
    }
    //대댓글 등록
    @PostMapping("/question/{id}/comment/{parentId}/child")
    public ResponseEntity<Long> createChildComment(@PathVariable Long parentId,@PathVariable Long id,
                                                   @RequestBody QuestionCommentRequestDTO requestDTO) {
        Long childCommentId = questionCommentService.saveChildComment(parentId, id,requestDTO);
        return ResponseEntity.ok(childCommentId);
    }


    // 대댓글만 조회(테스트용)
    @GetMapping("/question/{id}/comment/{commentId}/children")
    public ResponseEntity<List<QuestionCommentResponseDTO>> getChildComments(@PathVariable Long commentId) {
        List<QuestionCommentResponseDTO> childComments = questionCommentService.findAllChildComments(commentId);
        return ResponseEntity.ok(childComments);
    }

}