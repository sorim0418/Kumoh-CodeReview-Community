package com.kcr.repository;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    Page<QuestionComment> findAll(Pageable pageable);
   // Page<QuestionComment> findByQuestionId(Long questionId,Pageable pageable);

    List<QuestionComment> findAllByQuestionId(Long questionID);
    //공감
    @Modifying
    @Query("update QuestionComment q set q.likes = q.likes + 1 where q.id=:id")
    void updateLikes(@Param("id")Long id);

    //공감취소
    @Modifying
    @Query("update QuestionComment q set q.likes = q.likes -1 where q.id=:id")
    void cancelLikes(@Param("id")Long id);

    /*public List<QuestionComment> findByQuestionId(Long questionId) {

        return queryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .fetchJoin()
                .where(comment.ticket.id.eq(questionId))
                .orderBy(
                        comment.parent.id.asc().nullsFirst(),
                        comment.createdAt.asc()
                ).fetch();
    }*/
    Optional<Object> findById(QuestionComment parentId);
}


