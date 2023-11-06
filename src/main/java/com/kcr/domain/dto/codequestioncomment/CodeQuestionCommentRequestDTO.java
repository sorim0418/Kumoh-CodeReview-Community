package com.kcr.domain.dto.codequestioncomment;

import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CodeQuestionCommentRequestDTO {
        private Long id;
        private String content;
        private Long likes;
        private String writer;
        private CodeQuestion codeQuestion;
        private CodeQuestionComment parentId;

        @Builder
        public  CodeQuestionCommentRequestDTO(String content,Long likes, String writer) {
            this.content = content;
            this.likes = likes;
            this.writer = writer;
        }

        //댓글 등록할때 들어오는거
        /* DTO -> Entity */
        public CodeQuestionComment toSaveEntity() {
            return CodeQuestionComment.builder()
                    .id(id)
                    .content(content)
                    .writer(writer)
                    .likes(0L)
                    .codeQuestion(codeQuestion)
                    .build();
        }
    }


