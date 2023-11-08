package com.kcr.controller;

import com.kcr.domain.entity.Question;
import com.kcr.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionControllerTest {
    private final QuestionController questionController;

    @Autowired
    public QuestionControllerTest(QuestionController questionController) {
        this.questionController = questionController;
    }

    @Test
    void 테스트() {

    }
}