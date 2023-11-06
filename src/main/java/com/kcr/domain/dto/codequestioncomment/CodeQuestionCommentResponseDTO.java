package com.kcr.domain.dto.codequestioncomment;

import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.QuestionComment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CodeQuestionCommentResponseDTO {
    private Long code_question_comment_id;
    private String content;
    private Long likes;
    private String writer;
    private Long code_question_id;

    @Builder
    public  CodeQuestionCommentResponseDTO(Long code_question_comment_id,String content,Long likes, String writer,Long code_question_id) {
        this.code_question_comment_id=code_question_comment_id;
        this.content = content;
        this.likes = likes;
        this.writer = writer;
        this.code_question_id=code_question_id;
    }

    @Builder
    public CodeQuestionCommentResponseDTO(CodeQuestionComment codeQuestionComment) {
        this.code_question_comment_id=codeQuestionComment.getId();
        this.content=codeQuestionComment.getContent();
        this.likes=codeQuestionComment.getLikes();
        this.writer=codeQuestionComment.getWriter();
        this.code_question_id=codeQuestionComment.getCodeQuestion().getId();
    }

    public static CodeQuestionCommentResponseDTO toCommentDTO(CodeQuestionComment codeQuestionComment, Long codeQuestionId) {
        CodeQuestionCommentResponseDTO codeQuestionCommentResponseDTO = new CodeQuestionCommentResponseDTO();
        codeQuestionCommentResponseDTO.setCode_question_comment_id(codeQuestionComment.getId());
        codeQuestionCommentResponseDTO.setContent(codeQuestionComment.getContent());
        codeQuestionCommentResponseDTO.setLikes(codeQuestionComment.getLikes());
        codeQuestionCommentResponseDTO.setWriter(codeQuestionComment.getWriter());
        codeQuestionCommentResponseDTO.setCode_question_id(codeQuestionId);
        return codeQuestionCommentResponseDTO;
    }
}
