package com.kcr.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//구성
public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String API_KEY = "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";
    public static final String BEARER = "Bearer ";
    public static final String CHAT_MODEL = "gpt-3.5-turbo";
    public static final Double TOP_P = 1.0;
    public static final Integer MAX_TOKEN = 300;
    public static final Boolean STREAM = false;
    public static final String ROLE = "user";
    public static final Double TEMPERATURE = 0.6;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String CHAT_URL = "https://api.openai.com/v1/chat/completions";

}
