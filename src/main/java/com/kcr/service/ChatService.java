package com.kcr.service;

import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.dto.chatGPT.ChatGptConfig;
import com.kcr.domain.dto.chatGPT.Choice;
import com.kcr.domain.dto.chatGPT.ChatGptRequest;
import com.kcr.domain.entity.Question;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {




   public String chat(String prompt){

       return prompt;
   }

}