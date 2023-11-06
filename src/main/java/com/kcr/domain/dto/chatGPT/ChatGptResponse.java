package com.kcr.domain.dto.chatGPT;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//GPT 응답 반환
@Data
public class ChatGptResponse{
    private String id;
    private String object;
    private String model;
    private LocalDate created;
    private List<Choice> choices;
}

//ChatGptResponseDto

