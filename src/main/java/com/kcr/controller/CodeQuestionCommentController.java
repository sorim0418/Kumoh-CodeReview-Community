package com.kcr.controller;

import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentRequestDTO;
import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.Question;
import com.kcr.repository.CodeQuestionCommentRepository;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.service.CodeQuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CodeQuestionCommentController {
    @Autowired
    private CodeQuestionCommentService codeQuestionCommentService;
    @Autowired
    private CodeQuestionCommentRepository codeQuestionCommentRepository;
    @Autowired
private CodeQuestionRepository codeQuestionRepository;
    //댓글 수정 , 부분수정이여서 PatchMapping 으로 바꿈
    @PatchMapping("/codequestion/{codequestionid}/comments/{id}")
    public ResponseEntity<Long> update(@PathVariable Long codequestionid,
                                       @PathVariable Long id,
                                       @RequestBody CodeQuestionCommentRequestDTO requestDTO) {
        codeQuestionCommentService.updateComment(codequestionid, id, requestDTO);
        return ResponseEntity.ok(id);
    }

    // 댓글 삭제
    @DeleteMapping("/codequestion/{codequestionid}/comments/{id}/delete")
    public void delete(@PathVariable("id") Long id) {
        codeQuestionCommentService.delete(id);
    }


    //댓글등록
    @PostMapping("/codequestion/{id}/comment")
    public ResponseEntity<Long> commentSave(@PathVariable Long id, @RequestBody CodeQuestionCommentRequestDTO codeQuestionCommentRequestDTO) {
        return ResponseEntity.ok(codeQuestionCommentService.commentSave(id, codeQuestionCommentRequestDTO));
    }
    //대댓글 등록
    @PostMapping("/codequestion/{id}/comment/{parentId}/child")
    public ResponseEntity<Long> createChildComment(@PathVariable Long parentId,@PathVariable Long id,
                                                   @RequestBody CodeQuestionCommentRequestDTO requestDTO) {
        Long childCommentId = codeQuestionCommentService.saveChildComment(parentId, id,requestDTO);
        return ResponseEntity.ok(childCommentId);
    }


    // 대댓글만 조회(테스트용)
    @GetMapping("/question/{id}/comment/{commentId}/children")
    public ResponseEntity<List<CodeQuestionCommentResponseDTO>> getChildComments(@PathVariable Long commentId) {
        List<CodeQuestionCommentResponseDTO> childComments = codeQuestionCommentService.findAllChildComments(commentId);
        return ResponseEntity.ok(childComments);
    }

}
