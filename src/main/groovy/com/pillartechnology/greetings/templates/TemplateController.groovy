package com.pillartechnology.greetings.templates

class TemplateController {

    TemplateResponse getTemplate(String name) {
        return new TemplateResponse(template: "test template")
    }

    static class TemplateResponse {
        String template
    }
}
