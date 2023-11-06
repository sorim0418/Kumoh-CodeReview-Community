package com.kcr.controller.api;

import com.kcr.domain.dto.chatGPT.ChatGptRequest;
import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.dto.chatGPT.Message;
import com.kcr.domain.dto.chatGPT.QuestionRequest;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.ChatGPT;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.ChatGPTRepository;
import com.kcr.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/chatgpt")
@Slf4j
@RequiredArgsConstructor
public class ChatGPTApiController {

    private final ChatService chatService;
    private final ChatGPTRepository chatGPTRepository;

    @PostMapping("/ask") //localhost:8086/chatgpt/ask
    public ChatGptResponse sendMessage(@RequestBody QuestionRequest questionRequest) {
        Message message = new Message(questionRequest.getQuestion());
        return chatService.askQuestion(questionRequest);
    }
    // question 아이디 받아와서 그 게시물에 대한 응답 반환
    /*@GetMapping("/kcr/questioncomment")
    public ResponseEntity<Page<QuestionCommentResponseDTO>> findAll(@PageableDefault(sort = "likes", direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        Page<QuestionComment> questionComments = questionCommentRepository.findAll(pageable);
        Page<QuestionCommentResponseDTO> response = questionComments.map(QuestionCommentResponseDTO::new);
        return ResponseEntity.ok(response);
    }*/

}
