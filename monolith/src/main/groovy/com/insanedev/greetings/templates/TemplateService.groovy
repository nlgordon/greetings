package com.insanedev.greetings.templates

interface TemplateService {
    Template getTemplate(String template)

    Template addTemplate(String name, String template)

    Template addTemplate(Template template)

    boolean hasTemplate(String name)

    void truncateTemplates()

    Map<String, Template> getAllTemplates()
}
