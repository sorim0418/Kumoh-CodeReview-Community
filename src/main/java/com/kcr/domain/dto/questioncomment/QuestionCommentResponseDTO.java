package com.kcr.domain.dto.questioncomment;

import com.kcr.domain.entity.QuestionComment;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class QuestionCommentResponseDTO {
    private Long question_comment_id;
    private String content;
    private Long likes;
    private String writer;
    private Long question_id;


    @Builder
    public QuestionCommentResponseDTO(Long question_comment_id, String content, Long likes, String writer,Long question_id) {
        this.question_comment_id = question_comment_id;
        this.content = content;
        this.likes = likes;
        this.writer = writer;
        this.question_id= question_id;
    }
    @Builder
    public QuestionCommentResponseDTO(QuestionComment questionComment) {
        this.question_comment_id=questionComment.getId();
        this.content=questionComment.getContent();
        this.likes=questionComment.getLikes();
        this.writer=questionComment.getWriter();
        this.question_id=questionComment.getQuestion().getId();
    }
    public static QuestionCommentResponseDTO toCommentDTO(QuestionComment questionComment, Long questionId) {
        QuestionCommentResponseDTO questionCommentResponseDTO = new QuestionCommentResponseDTO();
        questionCommentResponseDTO.setQuestion_comment_id(questionComment.getId());
        questionCommentResponseDTO.setContent(questionComment.getContent());
        questionCommentResponseDTO.setLikes(questionComment.getLikes());
        questionCommentResponseDTO.setWriter(questionComment.getWriter());
        questionCommentResponseDTO.setQuestion_id(questionId);
        return questionCommentResponseDTO;
    }
}
