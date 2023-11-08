package com.kcr.service;

import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.entity.Question;
import com.kcr.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {
    private final QuestionService questionService;

    @Autowired
    public QuestionServiceTest(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Test
    void 테스트() {
        QuestionResponseDTO byId = questionService.findById(1L);

        System.out.println(byId.getContent());
    }

}