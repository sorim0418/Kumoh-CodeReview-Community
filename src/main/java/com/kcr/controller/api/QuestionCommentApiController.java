package com.kcr.controller.api;

import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentRequestDTO;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.QuestionCommentRepository;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionCommentApiController {
    @Autowired
    private QuestionCommentService questionCommentService;
    @Autowired
    private QuestionCommentRepository questionCommentRepository;
    @Autowired
    private QuestionRepository questionRepository;

    //댓글등록
    @PostMapping("/questioncomment/create") //kcr/question
    public Long postSave(@RequestBody QuestionCommentRequestDTO questionCommentRequestDTO) {
        return questionCommentService.save(questionCommentRequestDTO);
    }

    //댓글 수정
    /*@Transactional
    @PutMapping("/questioncomment/update/{id}")
    public QuestionCommentRequestDTO update(@PathVariable Long id, @RequestBody QuestionCommentRequestDTO requestQuestionCommentDTO) {
        System.out.println("id: " + id);
        System.out.println("바뀐내용입니다 : " + requestQuestionCommentDTO.getContent());
        questionCommentService.update(id, requestQuestionCommentDTO);
        return requestQuestionCommentDTO;
    }*/

   @PutMapping({"/posts/{postsId}/comments/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long questionId, @PathVariable Long id, @RequestBody QuestionRequestDTO questionRequestDTO) {
        questionCommentService.updateComment(questionId, id, questionRequestDTO);
        return ResponseEntity.ok(id);
    }


    // 댓글 삭제
    @DeleteMapping("/questioncomment/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        questionCommentService.delete(id);
    }

    //댓글 좋아요
    @PutMapping("/questioncomment/likes/{id}")
    public void likeComment(@PathVariable("id") Long id) {
        questionCommentService.updateLikes(id);
    }

    //댓글 좋아요 취소
    @PutMapping("/questioncomment/cancellikes/{id}")
    public void likeCancel(@PathVariable("id") Long id) {
        questionCommentService.cancelLikes(id);
    }

    @PostMapping("/question/{id}/comment")
    public ResponseEntity<Long> commentSave(@PathVariable Long id, @RequestBody QuestionCommentRequestDTO questionCommentRequestDTO) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        return ResponseEntity.ok(questionCommentService.commentSave(id, questionCommentRequestDTO));
    }
}
    /*@PostConstruct
    public void init() {
        for(int i = 1; i <= 100; i++) {
            questionCommentRepository.save(new QuestionComment("content" + i, 0L, "writer" + i));
        }
    }*/

