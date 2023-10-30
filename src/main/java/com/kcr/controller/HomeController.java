package com.kcr.controller;

import com.kcr.domain.dto.question.QuestionListResponseDTO;
import com.kcr.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeRepository homeRepository;

    @GetMapping("/")
    public Page<QuestionListResponseDTO> findTop5(Pageable pageable) {
        return homeRepository.findTop5ByOrderByCreateDateDesc(pageable);
    }
}
