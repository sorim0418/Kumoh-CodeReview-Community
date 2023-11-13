package com.kcr.domain.dto.chatGPT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kcr.domain.entity.ChatGPT;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//chatGPT에 요청할 DTO Format
public class ChatGptRequest implements Serializable {
   // private Long id;
    private String model;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    private Boolean stream;
    private List<ChatGptMessage> messages;
    //@JsonProperty("top_p")
    //private Double topP;
    @Builder
    public ChatGptRequest(String model, Integer maxTokens, Double temperature,
                          Boolean stream, List<ChatGptMessage> messages
            /*,Double topP*/) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
        //this.topP = topP;
    }

}