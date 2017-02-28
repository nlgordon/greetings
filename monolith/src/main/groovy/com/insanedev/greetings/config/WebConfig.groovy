package com.insanedev.greetings.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfig {

    @Bean
//    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate()
    }
}
