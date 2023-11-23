package com.kcr.domain.dto.question;

import com.kcr.domain.entity.Likes;
import com.kcr.domain.entity.Question;
import lombok.*;

import java.util.List;

/* 게시글의 등록과 수정을 처리할 요청 클래스 */
@Getter
@AllArgsConstructor
@NoArgsConstructor // (access = AccessLevel.PROTECTED)
public class QuestionRequestDTO {
    private String title;
    private String writer;
    private String content;
    private String createDate;
    private String modifiedDate;
    private List<Likes> likes;
    private Long views;
//        private Member member;

    /* 게시글 등록 */
    @Builder
    public QuestionRequestDTO(String title, String writer, String content, String createDate,List<Likes> likes, Long views) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createDate = createDate;
        this.likes = likes;
        this.views = views;
//            this.member = member;
    }

    /* DTO -> Entity */
    public Question toSaveEntity() {

        return Question.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .likes(likes)
                .views(0L)
//                    .member(member)
                .build();
    }
}