package com.insanedev.greetings

import com.insanedev.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingServiceImpl implements GreetingService {

    @Autowired
    TemplateService templateService
    Map<UUID, Greeting> greetings = [:]

    @Override
    Greeting generateGreeting(String template) {
        Greeting greeting = new Greeting()
        greeting.greeting = getTemplate(template)

        return greeting
    }

    @Override
    Greeting saveGreeting(Greeting greeting) {
        greeting.id = UUID.randomUUID()
        greeting.greeting = getTemplate(greeting.templateName)
        greetings[greeting.id] = greeting
        return greeting
    }

    @Override
    Greeting getGreeting(UUID uuid) {
        return greetings[uuid]
    }

    String getTemplate(String template) {
        return templateService.getTemplate(template).template
    }
}
