package com.kcr.repository;
import com.kcr.domain.entity.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    Page<QuestionComment> findAll(Pageable pageable);
    List<QuestionComment> findAllByQuestionId(Long questionID);
    //부모댓글이 없을때(최상위 댓글일때)
    List<QuestionComment> findByQuestionIdAndParentIsNull(Long questionId);

    @Query("SELECT qc FROM QuestionComment qc WHERE qc.parent.id = :parentId")
    List<QuestionComment> findChildCommentsByParentId(@Param("parentId") Long parentId);

    @Query("select c from QuestionComment c left join fetch c.parent where c.id = :id")
    Optional<QuestionComment> findWithParentById(Long id);


    @Query("SELECT qc FROM QuestionComment qc LEFT JOIN FETCH qc.parent WHERE qc.question.id = :questionId")
    List<QuestionComment> findAllWithRepliesByQuestionId(@Param("questionId") Long questionId);


}

