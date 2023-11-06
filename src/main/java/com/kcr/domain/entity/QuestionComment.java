package com.kcr.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "questioncomment")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자의 접근 제어를 Protected로 설정함으로써 무분별한 객체 생성을 예방함
@AllArgsConstructor
@Builder
public class QuestionComment {

    @Id @GeneratedValue
    @Column(name = "QUESTION_COMMENT_ID")
    private Long id;

    private String writer;

    @NotEmpty
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private Long likes;

    /* 연관관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private QuestionComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<QuestionComment> child = new ArrayList<>();

    public QuestionComment(String content,Long likes, String writer, Question question_id){
        this.content = content;
        this.likes = likes;
        this.writer = writer;
        this.question=question_id;
    }

    // Getters and setters

    public void updateQuestionComment(String content) {
        this.content = content;
    }
    public void updateParent(QuestionComment parentComment){}

    /*public static QuestionCommentRequestDTO toSaveComment(QuestionComment questionComment, Question question) {
        QuestionCommentRequestDTO questionCommentDTO = new QuestionCommentRequestDTO();
        questionCommentDTO.setContent(questionComment.getContent());
        questionCommentDTO.setWriter(questionComment.getWriter());
        return questionCommentDTO;
    }*/
}
