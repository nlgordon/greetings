package com.pillartechnology.greetings.templates

class TemplateController {

    TemplateService templateService

    TemplateResponse getTemplate(String name) {
        return new TemplateResponse(template: templateService.getTemplate(name))
    }

    static class TemplateResponse {
        String template
    }
}
