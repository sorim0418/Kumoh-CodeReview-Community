package com.kcr.controller;


import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionCommentController {
   /* @Autowired
    private QuestionCommentService questionCommentService;

    //공감순 댓글 조회
   *//* @GetMapping("/kcr/questioncomment")
    public Page<QuestionCommentResponseDTO> findAllByCreateDate(@PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        return questionCommentRepository.findAll(pageable)
                .map(QuestionCommentResponseDTO::new);
    }
 @GetMapping("/question/{id}/comments")
    public ResponseEntity<Page<QuestionComment>> getCommentsByQuestionId(@PathVariable Long id, @PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        Page<QuestionComment> comments = questionCommentService.getCommentsByQuestionId(id,pageable);
        return ResponseEntity.ok(comments);
    }*//*
    @GetMapping("/question/{id}/comments")
    public ResponseEntity<List<QuestionCommentResponseDTO>> getCommentsByQuestionId(@PathVariable Long id, @PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        //Page<QuestionComment> comments = questionCommentService.getCommentsByQuestionId(id,pageable);
        List<QuestionCommentResponseDTO> questionCommentDTOList = questionCommentService.findAll(id); //댓글 목록 가져옴
        return ResponseEntity.ok(questionCommentDTOList);
    }*/


}

