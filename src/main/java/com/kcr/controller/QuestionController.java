package com.kcr.controller;

import com.kcr.domain.dto.question.QuestionListResponseDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/* UI Controller */
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    /* 게시글 등록 화면 */
    @GetMapping("/question/add")
    public Model addQuestion(Model model) {
        return model.addAttribute("questionRequestDTO", new QuestionRequestDTO());
    }

    /* 게시글 수정 화면 */
    @GetMapping("/question/{id}/edit")
    public Model updateQuestion(@PathVariable("id") Long id, Model model) {
        QuestionResponseDTO questionResponseDTO = questionService.findById(id);

         QuestionResponseDTO.builder()
                .id(questionResponseDTO.getId())
                .title(questionResponseDTO.getTitle())
                .writer(questionResponseDTO.getWriter())
                .content(questionResponseDTO.getContent())
                .createDate(questionResponseDTO.getCreateDate())
                .likes(questionResponseDTO.getLikes())
                .views(questionResponseDTO.getViews())
                .build();

        return model.addAttribute("questionResponseDTO", questionResponseDTO);
    }

    /* 게시글 전체 조회 화면 + 최신순 정렬*/
    @GetMapping("/question")
    public Page<QuestionListResponseDTO> findAllByCreateDate(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(QuestionListResponseDTO::new);
    }

    /* 게시글 전체 조회 화면 + 공감순 정렬 */
    @GetMapping("/questionByLikes")
    public Page<QuestionListResponseDTO> findAllByLikes(@PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(QuestionListResponseDTO::new);
    }

    /* 게시글 상세 조회 + 조회수 업데이트 */
    @GetMapping("/question/{id}")
    public QuestionResponseDTO findById(@PathVariable("id") Long id) {
        QuestionResponseDTO questionResponseDTO = questionService.findById(id);
        questionService.updateViews(id); // views++

        return questionResponseDTO;
    }

    /* 게시글 제목 검색 */
    @GetMapping("/question/search")
    public Page<QuestionListResponseDTO> searchByTitle(String title, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return questionService.searchByTitle(title, pageable)
                .map(QuestionListResponseDTO::new);
    }

    /* 게시글 작성자 검색 */
    @GetMapping("/question/searchByWriter")
    public Page<QuestionListResponseDTO> searchByWriter(String writer, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return questionService.searchByTitle(writer, pageable)
                .map(QuestionListResponseDTO::new);
    }
}
