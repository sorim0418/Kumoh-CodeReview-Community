package com.kcr.controller;

import com.kcr.domain.dto.codequestion.CodeQuestionRequestDTO;
import com.kcr.domain.dto.codequestion.CodeQuestionResponseDTO;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.service.CodeQuestionService;
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
public class CodeQuestionController {

    private final CodeQuestionService codeQuestionService;
    private final CodeQuestionRepository codeQuestionRepository;

    /* 게시글 등록 화면 */
    @GetMapping("/codequestion/add")
    public String addUI(Model model) {
        model.addAttribute("codeQuestionRequestDTO", new CodeQuestionRequestDTO());

        return "/codeQuestions/addCodeQuestion";
    }

    /* 게시글 수정 화면 */
    @GetMapping("/codequestion/{id}/edit")
    public String updateUI(@PathVariable("id") Long id, Model model) {
        CodeQuestionResponseDTO codeQuestionResponseDTO = codeQuestionService.findById(id);

        CodeQuestionResponseDTO.builder()
                .id(codeQuestionResponseDTO.getId())
                .title(codeQuestionResponseDTO.getTitle())
                .writer(codeQuestionResponseDTO.getWriter())
                .content(codeQuestionResponseDTO.getContent())
                .createDate(codeQuestionResponseDTO.getCreateDate())
                .likes(codeQuestionResponseDTO.getLikes())
                .views(codeQuestionResponseDTO.getViews())
                .build();

        model.addAttribute("codeQuestionResponseDTO", codeQuestionResponseDTO);
            return "codeQuestions/updateCodeQuestion";
    }

    /* 게시글 전체 조회 화면 + 최신순 정렬*/
    @GetMapping("/codequestion")
    public Page<CodeQuestionResponseDTO> listByCreateDate(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return codeQuestionRepository.findAll(pageable)
                .map(CodeQuestionResponseDTO::new);
    }

    /* 게시글 전체 조회 화면 + 공감순 정렬 */
    @GetMapping("/codequestionByLikes")
    public Page<CodeQuestionResponseDTO> listByLikes(@PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return codeQuestionRepository.findAll(pageable)
                .map(CodeQuestionResponseDTO::new);
    }

    /* 게시글 상세 조회 + 조회수 업데이트 */
    @GetMapping("/codequestion/{id}")
    public CodeQuestionResponseDTO listDetail(@PathVariable("id") Long id) {
        CodeQuestionResponseDTO codeQuestionResponseDTO = codeQuestionService.findById(id);
        codeQuestionService.updateViews(id); // views++

        return codeQuestionResponseDTO;
    }
}
