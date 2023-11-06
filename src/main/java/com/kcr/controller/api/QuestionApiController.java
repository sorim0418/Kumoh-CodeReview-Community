package com.kcr.controller.api;

import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.entity.Question;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/* API Controller */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionApiController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    /* 게시글 등록 */
    @PostMapping("/question")
    public Long save(@RequestBody QuestionRequestDTO requestDTO) {
        return questionService.save(requestDTO);
    }

    /* 게시글 수정 */
    @PatchMapping("/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionRequestDTO requestDTO) {
        return questionService.update(id, requestDTO);
    }

    /* 게시글 삭제 */
    @DeleteMapping("/question/{id}")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

    /* 게시글 좋아요 업데이트 */


    // 테스트 데이터
   /* @PostConstruct
    public void init() {
        for(int i = 1; i <= 100; i++) {
            questionRepository.save(new Question("title" + i, "writer" + i, "content" + i, 100L + i, 100L));
        }
    }*/
}
