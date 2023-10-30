package com.kcr.domain.dto.questioncomment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.kcr.domain.entity.QuestionComment;
@Getter
@NoArgsConstructor
public class QuestionCommentRequestDTO {
    private String content;
    private Long likes;
    private String writer;

    @Builder
    public QuestionCommentRequestDTO(String content,Long likes, String writer) {
        this.content = content;
        this.likes = likes;
        this.writer = writer;
    }


    //댓글 등록할때 들어오는거
    /* DTO -> Entity */
    public QuestionComment toSaveEntity() {
        return QuestionComment.builder()
                .content(content)
                .writer(writer)
                .likes(0L)
                .build();
    }
}
