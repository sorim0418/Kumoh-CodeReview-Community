package com.kcr.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    //  public static final String API_KEY ="myapiKey";
    public static final String BEARER = "Bearer ";
    public static final String CHAT_MODEL = "gpt-3.5-turbo";
    public static final Integer MAX_TOKEN = 600;
    public static final Boolean STREAM = false; //true 로하면 gpt에서 응답 안옴
    public static final String ROLE = "user";
    public static final Double TEMPERATURE = 0.8;
    //public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String CHAT_URL = "https://api.openai.com/v1/chat/completions";
}
