package com.kcr.domain.dto.chatGPT;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse{

    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private GPTMessage message;
    }

}

//ChatGptResponseDto

