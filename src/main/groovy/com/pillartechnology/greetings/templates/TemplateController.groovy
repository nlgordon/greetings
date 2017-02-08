package com.pillartechnology.greetings.templates

import org.springframework.boot.context.config.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController {

    TemplateService templateService

    @RequestMapping("/api/template/{name}")
    ResponseEntity<TemplateResponse> getTemplate(@PathVariable String name) {
        String template = templateService.getTemplate(name)
        if (template == null) {
            return new ResponseEntity<String>("Template $name was not found", HttpStatus.NOT_FOUND)
        }

        return new ResponseEntity<TemplateResponse>(new TemplateResponse(template: template), HttpStatus.OK)
    }

    static class TemplateResponse {
        String template
    }
}
