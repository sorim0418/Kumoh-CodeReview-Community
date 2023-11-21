package com.kcr.repository;

import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.Push;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushRepository extends JpaRepository<Push, Long> {
}
