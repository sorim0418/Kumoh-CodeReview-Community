package com.kcr.domain.dto.chatGPT;


import com.kcr.domain.entity.Question;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatGptMessage {
    private String role;
    private String content;
   // private Long questionid;
    @Builder
    public ChatGptMessage(String role, String content){
        this.role = role;
        this.content=content;
    }
}
