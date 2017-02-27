package com.insanedev.testing.e2e

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class TestConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate()
    }
}
