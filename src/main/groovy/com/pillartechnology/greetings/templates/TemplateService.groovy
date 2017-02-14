package com.pillartechnology.greetings.templates

interface TemplateService {
    Template getTemplate(String template)

    Template addTemplate(String name, String template)

    boolean hasTemplate(String name)
}
