package com.kcr.domain.entity;

import jdk.jshell.Snippet;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "codequestioncomment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CodeQuestionComment {

    @Id @GeneratedValue
    @Column(name = "CODE_QUESTION_COMMENT_ID")
    private Long id;

    private String writer;

    @NotEmpty
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private Long likes;
    private boolean isRemoved;

    /* 연관관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CODE_QUESTION_ID")
    private CodeQuestion codeQuestion;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private QuestionComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<QuestionComment> child = new ArrayList<>();
    public CodeQuestionComment(String content,Long likes, String writer, CodeQuestion codeQuestion_id){
        this.content = content;
        this.likes = likes;
        this.writer = writer;
        this.codeQuestion=codeQuestion_id;
    }

    //댓글수정
    public void updateCodeQuestionComment(String content) {
        this.content = content;
    }
}
