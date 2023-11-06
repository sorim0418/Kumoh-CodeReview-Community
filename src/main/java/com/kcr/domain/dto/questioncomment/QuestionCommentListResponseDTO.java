package com.kcr.domain.dto.questioncomment;

import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionCommentListResponseDTO {
    private Long question_comment_id;
    private String content;
    private Long is_removed;
    private Long likes;
    private String writer;

    @Builder
    public QuestionCommentListResponseDTO(Long question_comment_id, String content, Long is_removed, Long likes, String writer) {
        this.question_comment_id = question_comment_id;
        this.content = content;
        this.likes = likes;
        this.writer = writer;
    }

    public QuestionCommentListResponseDTO(QuestionComment questionComment) {
        this.question_comment_id = questionComment.getId();
        this.content = questionComment.getContent();
        this.likes = questionComment.getLikes();
        this.writer = questionComment.getWriter();
//            this.memberId = question.getMember().getId();
    }

}
