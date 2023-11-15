package com.kcr.domain.dto.questioncomment;

import com.kcr.domain.entity.QuestionComment;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Getter
@Setter
public class QuestionCommentResponseDTO {
    private Long question_comment_id;
    private String content;
    private Long likes;
    private String writer;
    private Long question_id;
    private String createDate;
    private List<QuestionCommentResponseDTO> childComments;
    @Builder
    public QuestionCommentResponseDTO(Long question_comment_id, String content, Long likes, String writer,Long question_id,List<QuestionCommentResponseDTO> childComments) {
        this.question_comment_id = question_comment_id;
        this.content = content;
        this.likes = likes;
        this.writer = writer;
        this.question_id= question_id;
        this.childComments = childComments;
    }
    @Builder
    public QuestionCommentResponseDTO(QuestionComment questionComment) {
        this.question_comment_id=questionComment.getId();
        this.content=questionComment.getContent();
        this.likes=questionComment.getLikes();
        this.writer=questionComment.getWriter();
        this.createDate = questionComment.getCreateDate();
        this.question_id=questionComment.getQuestion().getId();
    }

    public static QuestionCommentResponseDTO toCommentDTO2(QuestionComment questionComment) {
        // 자식 댓글 리스트 초기화
        List<QuestionCommentResponseDTO> childCommentsDTO = new ArrayList<>();

        // 자식 댓글 처리
        if (questionComment.getChild() != null) {
            for (QuestionComment childComment : questionComment.getChild()) {
                // 부모 댓글의 ID와 다른 경우에만 자식 댓글 목록에 추가
                if (!childComment.getParent().getId().equals(questionComment.getId())) {
                    childCommentsDTO.add(toCommentDTO2(childComment));
                }
            }
        }

        // DTO 생성
        return QuestionCommentResponseDTO.builder()
                .question_comment_id(questionComment.getId())
                .content(questionComment.getContent())
                .likes(questionComment.getLikes())
                .writer(questionComment.getWriter())
                .question_id(questionComment.getQuestion().getId())
                .childComments(childCommentsDTO)
                .build();
    }
}