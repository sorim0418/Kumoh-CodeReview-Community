package com.kcr.repository;

import com.kcr.domain.entity.Question;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionRepositoryTest(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Test
    void 테스트() {
        Question byId = questionRepository.findById(1L).get();

        System.out.println(byId.getContent());
    }
}