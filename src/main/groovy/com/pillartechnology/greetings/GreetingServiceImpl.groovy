package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.Template
import com.pillartechnology.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingServiceImpl implements GreetingService {

    @Autowired
    TemplateService templateService
    Map<UUID, Greeting> greetings = [:]

    @Override
    Greeting generateGreeting(String template) {

        Greeting ret = new Greeting()
        ret.greeting = templateService.getTemplate(template).template

        return ret
    }

    @Override
    Greeting saveGreeting(Greeting greeting) {
        greeting.id = UUID.randomUUID()
        Template template = templateService.getTemplate(greeting.templateName)
        greeting.greeting = template.template
        greetings[greeting.id] = greeting
        return greeting
    }

    @Override
    Greeting getGreeting(UUID uuid) {
        return greetings[uuid]
    }
}
