package com.pillartechnology.greetings.templates

import org.springframework.stereotype.Component

@Component
class TemplateServiceImpl implements TemplateService {

    Map<String, String> templates = [:]

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

        if (templates.containsKey(template)) {
            return templates[template]
        }

        return null
    }

    void addTemplate(String name, String template) {
        templates[name] = template
    }

    boolean hasTemplate(String name) {
        return templates.containsKey(name)
    }
}
