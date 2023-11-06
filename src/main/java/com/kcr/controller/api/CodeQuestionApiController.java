package com.kcr.controller.api;

import com.kcr.domain.dto.codequestion.CodeQuestionRequestDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.service.CodeQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/* API Controller */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CodeQuestionApiController {

    private final CodeQuestionService codeQuestionService;
    private final CodeQuestionRepository codeQuestionRepository;

    /* 게시글 등록 */
    @PostMapping("/codequestion")
    public Long save(@RequestBody CodeQuestionRequestDTO requestDTO) {
        return codeQuestionService.save(requestDTO);
    }

    /* 게시글 좋아요 업데이트 */


    /* 게시글 수정 */
    @PatchMapping("/codequestion/{id}")
    public Long update(@PathVariable Long id, @RequestBody CodeQuestionRequestDTO requestDTO) {
        return codeQuestionService.update(id, requestDTO);
    }

    /* 게시글 삭제 */
    @DeleteMapping("/codequestion/{id}")
    public void delete(@PathVariable Long id) {
        codeQuestionService.delete(id);
    }

    // 테스트 데이터
  /* @PostConstruct
    public void init() {
        for(int i = 1; i <= 100; i++) {
            codeQuestionRepository.save(new CodeQuestion("title" + i, "writer" + i, "content" + i, "codeContent" + i, 100L + i, 100L));
        }
    }*/
}
