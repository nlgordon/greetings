package com.insanedev.testing.e2e

import com.insanedev.greetings.templates.Template
import com.insanedev.greetings.templates.TemplateResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class TemplateHelperService {

    @Autowired
    RestTemplate restTemplate

    @Value('${templateUrl}')
    String templateUrl

    ResponseEntity<String> deleteTemplates() {
        return restTemplate.exchange(templateUrl, HttpMethod.DELETE, null, String)
    }

    ResponseEntity<TemplateResponse> createTemplate(Template template) {
        return restTemplate.exchange(templateUrl, HttpMethod.POST, new HttpEntity<>(template), TemplateResponse)
    }
}
