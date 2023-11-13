package com.kcr.controller;

import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.dto.question.QuestionListResponseDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.ChatGPT;
import com.kcr.domain.entity.Question;
import com.kcr.repository.ChatGPTRepository;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.ChatGptService;
import com.kcr.service.QuestionCommentService;
import com.kcr.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* UI Controller */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final QuestionCommentService questionCommentService;
    private final ChatGptService chatGptService;
    private final ChatGPTRepository chatGPTRepository;

    /* 게시글 등록 화면 */
//    @GetMapping("/question/add")
//    public Model addQuestion(Model model) {
//        return model.addAttribute("questionRequestDTO", new QuestionRequestDTO());
//    }

    /* 게시글 수정 화면 */
    @GetMapping("/question/{id}/edit")
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable("id") Long id) {
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

        return new ResponseEntity<>(questionResponseDTO, HttpStatus.OK);
    }

    /* 게시글 전체 조회 화면 + 최신순 정렬*/
    @GetMapping("/question")
    public ResponseEntity<Page<QuestionListResponseDTO>> findAllByCreateDate(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        Page<QuestionListResponseDTO> questionListResponseDTO = questionService.findAll(pageable);
        return new ResponseEntity<>(questionListResponseDTO, HttpStatus.OK);
    }

    /* 게시글 전체 조회 화면 + 공감순 정렬 */
    @GetMapping("/questionByLikes")
    public Page<QuestionListResponseDTO> findAllByLikes(@PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(QuestionListResponseDTO::new);
    }

    /* 게시글 상세 조회 + 조회수 업데이트 */
    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionResponseDTO> findById(@PathVariable("id") Long id,Model model) {
        QuestionResponseDTO questionResponseDTO = questionService.findById(id);
        questionService.updateViews(id); // views++
        String content = questionResponseDTO.getContent();
        log.info("질문: "+content);
        //gpt 댓글
         ChatGptResponse chatGptResponse = chatGptService.findById(id);
         questionResponseDTO.setChatGPT(chatGptResponse);
        //댓글
        List<QuestionCommentResponseDTO> questionCommentDTOList = questionCommentService.findAllWithChild(id);
        questionResponseDTO.setQuestionComments(questionCommentDTOList);

        model.addAttribute("questionCommentList", questionCommentDTOList);
        model.addAttribute("chatGPT", chatGptResponse);

       // model.addAttribute("question", questionResponseDTO);
        return new ResponseEntity<>(questionResponseDTO, HttpStatus.OK);
    }



}
