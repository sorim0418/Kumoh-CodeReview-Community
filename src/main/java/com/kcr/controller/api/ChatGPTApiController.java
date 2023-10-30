package com.kcr.controller.api;

import com.kcr.domain.dto.chatGPT.ChatGptRequest;
import com.kcr.domain.dto.chatGPT.ChatGptResponse;
import com.kcr.domain.entity.Question;
import com.kcr.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/chat-gpt")
@Slf4j
@RequiredArgsConstructor
public class ChatGPTApiController {
    private final ChatService chatgptService;
    private RestTemplate template;

   /*@PostMapping("/")
    public String chat(Model model, @ModelAttribute String prompt){
       try {
           model.addAttribute("request",prompt);
           model.addAttribute("response",chatgptService.chat(prompt));
       }catch (Exception e){
           model.addAttribute("response","CHAT GPT API ERROR");
           log.error("Exception",e);
       }
       return "";
   }*/

    public ChatGptResponse chat(@RequestParam("prompt") String prompt){
        ChatGptRequest request = new ChatGptRequest("gpt-3.5-turbo",prompt);
        return null;
    }

}
