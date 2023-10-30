package com.kcr.domain.dto.chatGPT;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChatGptConfig {

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

}
