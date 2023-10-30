package com.kcr.repository;

import com.kcr.domain.entity.CodeQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeQuestionRepository extends JpaRepository<CodeQuestion, Long> {

//    @Query(value = "select q from Question q left join q.member m",
//            countQuery = "select count(q) from Question q")
    Page<CodeQuestion> findAll(Pageable pageable);

    @Modifying
    @Query("update CodeQuestion q set q.views = q.views + 1 where q.id=:id")
    void updateViews(@Param("id") Long id);

    @Modifying
    @Query("update CodeQuestion q set q.likes = q.likes + 1 where q.id=:id")
    void updateLikes(@Param("id")Long id);
}
