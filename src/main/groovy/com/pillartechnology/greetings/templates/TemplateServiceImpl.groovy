package com.pillartechnology.greetings.templates

import org.springframework.stereotype.Component

@Component
class TemplateServiceImpl implements TemplateService {

    @Override
    String getTemplate(String template) {

        if (!template) {
            throw new IllegalArgumentException("No template specified")
        }

        if (template == 'hello') {
            return 'hello world'
        } else if (template == 'valentines') {
            return 'Happy Valentines Day!'
        }

        throw new IllegalArgumentException("Unknown template $template")
    }
}
