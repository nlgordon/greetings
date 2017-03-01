package com.insanedev.greetings.templates

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class TemplateServiceImpl implements TemplateService {

    @Autowired
    RestTemplate restTemplate

    Map<String, Template> templates = [:]

    @Override
    Template getTemplate(String template) {
        if (!template) {
            throw new IllegalArgumentException("No template specified")
        }

        if (hasTemplate(template)) {
            return templates[template]
        }

        return null
    }

    @Override
    Template addTemplate(String name, String template) {
        Template newTemplate = new Template(name: name, template: template)
        templates[name] = newTemplate

        return newTemplate
    }

    @Override
    Template addTemplate(Template template) {
        if (!template.name) {
            throw new IllegalArgumentException("Template must have a name")
        }
        templates[template.name] = template
    }

    @Override
    boolean hasTemplate(String name) {
        return templates.containsKey(name)
    }

    @Override
    void truncateTemplates() {
        templates = [:]
    }

    @Override
    Map getAllTemplates() {
        return restTemplate.getForObject("http://templates/api/template", Map)
//        return templates
    }
}
