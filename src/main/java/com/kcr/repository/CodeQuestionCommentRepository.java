package com.kcr.repository;

import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeQuestionCommentRepository extends JpaRepository<CodeQuestionComment, Long> {
    List<CodeQuestionComment> findAllByCodeQuestionId(Long codeQuestionId);

}
