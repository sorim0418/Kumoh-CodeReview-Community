package com.kcr.domain.dto.chatGPT;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPTMessage {
    private String role;
    private String content; //prompt
}
