package com.pillartechnology.greetings.templates

interface TemplateService {
    String getTemplate(String template)

    void addTemplate(String name, String template)

    boolean hasTemplate(String name)
}
