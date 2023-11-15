package com.kcr.controller;

import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.dto.question.QuestionListResponseDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.repository.QuestionRepository;
import com.kcr.service.ChatGptService;
import com.kcr.service.QuestionCommentService;
import com.kcr.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /* ================ API ================ */
    /* 게시글 등록 */
    @PostMapping("/api/question")
    public Long save(@RequestBody QuestionRequestDTO requestDTO) {
        return questionService.save(requestDTO);
    }

    /* 게시글 수정 */
    @PatchMapping("/api/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionRequestDTO requestDTO) {
        return questionService.update(id, requestDTO);
    }

    /* 게시글 삭제 */
    @DeleteMapping("/api/question/{id}")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }



    /* ================ UI ================ */
    /* 게시글 수정 화면 */
    @GetMapping("/question/{id}/edit")
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable("id") Long id) {
        QuestionResponseDTO responseDTO = questionService.findById(id);

        QuestionResponseDTO.builder()
                .id(responseDTO.getId())
                .title(responseDTO.getTitle())
                .writer(responseDTO.getWriter())
                .content(responseDTO.getContent())
                .createDate(responseDTO.getCreateDate())
                .likes(responseDTO.getLikes())
                .views(responseDTO.getViews())
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
        log.info("질문내용: "+content);
        //gpt 댓글
        ChatGptResponse chatGptResponse = chatGptService.findById(id);
        questionResponseDTO.setChatGPT(chatGptResponse);
        //댓글
      //  List<QuestionCommentResponseDTO> questionCommentDTOList = questionCommentService.findAllWithChild(id);
       // questionResponseDTO.setQuestionComments(questionCommentDTOList);

        //댓글 페이징 처리
        List<QuestionCommentResponseDTO> questionCommentDTOList = questionCommentService.findAllWithChild2(id, 1);
        questionResponseDTO.setQuestionComments(questionCommentDTOList);

        System.out.println("댓글 사이즈 : "+ questionCommentDTOList.size());
        for(int i = 0;i<questionCommentDTOList.size();i++){
            System.out.println("questionList : "+questionCommentDTOList.get(i).getQuestion_id());
        }

        model.addAttribute("questionCommentList", questionCommentDTOList);
        model.addAttribute("chatGPT", chatGptResponse);

        // model.addAttribute("question", questionResponseDTO);
        return new ResponseEntity<>(questionResponseDTO, HttpStatus.OK);
    }

}