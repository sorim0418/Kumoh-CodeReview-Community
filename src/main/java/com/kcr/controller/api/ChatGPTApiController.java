package com.kcr.controller.api;

import com.kcr.domain.dto.chatGPT.ChatGptMessage;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.chatGPT.ChatGptRequest;
import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.ChatGPT;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.ChatGPTRepository;
import com.kcr.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
@RestController
public class ChatGPTApiController {
    private final ChatGptService chatGptService;

   /* @PostMapping("/question/{id}/questiondump")
    public ResponseEntity sendQuestiontest2(@PathVariable Long id, @RequestBody QuestionRequestDTO questionRequestDTO) {
        ChatGptResponse chatGptResponse = null;
        String content = null; // 여기에 content 변수를 선언합니다.

        try {
            chatGptResponse = chatGptService.askQuestion2(id,questionRequestDTO);
        } catch (Exception e) {
            log.error("Error while processing the question: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        System.out.println(chatGptResponse);
        System.out.println(chatGptResponse.getChoices());
        System.out.println(chatGptResponse.getChoices().get(0));
        System.out.println(chatGptResponse.getChoices().get(0).getMessage());


        // 로그를 추가합니다.
        if (chatGptResponse != null) {
            log.debug("ChatGptResponse: " + chatGptResponse);
            if (chatGptResponse.getChoices() != null && !chatGptResponse.getChoices().isEmpty()) {
                ChatGptMessage message = chatGptResponse.getChoices().get(0).getMessage();
                if (message != null) {
                    content = message.getContent();
                    log.info("Message content: " + content);
                    // System.out.println("메시지 있음"+ content);
                } else {
                    log.info("Message is null");
                    //  System.out.println("Message is null"+ content);
                }
            } else {
                log.info("Choices list is empty or null");
                //  System.out.println("Choices list is empty or null"+ content);
            }
        } else {
            log.info("ChatGptResponse is null");
            //    System.out.println("ChatGptResponse is null"+ content);
        }

        // 응답 반환
        return ResponseEntity.ok(content);
    }*/

}
