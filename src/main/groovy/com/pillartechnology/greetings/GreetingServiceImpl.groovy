package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingServiceImpl implements GreetingService {

    @Autowired
    TemplateService templateService

    @Override
    Greeting generateGreeting(String template) {

        Greeting ret = new Greeting()
        ret.greeting = templateService.getTemplate(template).template

        return ret
    }
}
