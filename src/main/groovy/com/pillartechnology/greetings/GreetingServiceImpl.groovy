package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingServiceImpl implements GreetingService {

    @Autowired
    TemplateService templateService

    @Override
    String generateGreeting(String template) {

        return templateService.getTemplate(template).template
    }
}
