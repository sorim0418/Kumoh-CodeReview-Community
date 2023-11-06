package com.kcr.service;

import com.kcr.config.ChatGptConfig;
import com.kcr.domain.dto.chatGPT.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private static RestTemplate restTemplate = new RestTemplate();

    //    Build headers
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(chatRequest, headers);
    }

    //    Generate response
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.CHAT_URL,
                chatRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(QuestionRequest questionRequest) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(ChatGptConfig.ROLE, questionRequest.getQuestion()));

        ChatGptRequest chatGptRequest = new ChatGptRequest(
                ChatGptConfig.CHAT_MODEL,
                messages,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.MAX_TOKEN,
                ChatGptConfig.TOP_P);

        return this.getResponse(this.buildHttpEntity(chatGptRequest));
    }

}