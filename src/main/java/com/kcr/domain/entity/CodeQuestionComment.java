package com.kcr.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "codequestioncomment")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자의 접근 제어를 Protected로 설정함으로써 무분별한 객체 생성을 예방함
@AllArgsConstructor //얘 안넣어주면 @Builder annotation 에러뜸
public class CodeQuestionComment extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "CODE_QUESTION_COMMENT_ID")
    private Long id;

    private String writer;

    @NotEmpty
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @NotEmpty
    @Column(columnDefinition = "LONGTEXT", name = "CODE_CONTENT")
    private String codeContent;

    private Long likes;
    private boolean isRemoved;

    /* 연관관계 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CODE_QUESTION_ID")
    private CodeQuestion codeQuestion;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private CodeQuestionComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CodeQuestionComment> child = new ArrayList<>();

    public CodeQuestionComment(String content, String codeContent,Long likes, String writer, CodeQuestion codeQuestion_id){
        this.content = content;
        this.codeContent = codeContent;
        this.likes = likes;
        this.writer = writer;
        this.codeQuestion=codeQuestion_id;
    }

    public void updateCodeQuestionComment(String content) {
        this.content = content;
    }

    public void updateParent(CodeQuestionComment codeQuestionComment) {
    }
}
