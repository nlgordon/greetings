package com.pillartechnology.greetings.templates

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController {

    TemplateService templateService

    @RequestMapping("/api/template/{name}")
    ResponseEntity<TemplateResponse> getTemplate(@PathVariable String name) {
        Template template = templateService.getTemplate(name)
        if (template == null) {
            return new ResponseEntity<String>("Template $name was not found", HttpStatus.NOT_FOUND)
        }

        return new ResponseEntity<TemplateResponse>(new TemplateResponse(template: template), HttpStatus.OK)
    }

    @RequestMapping(path = "/api/template/{name}", method = [RequestMethod.POST])
    ResponseEntity<TemplateResponse> addTemplate(@PathVariable String name, @RequestParam String template) {
        Template newTemplate = templateService.addTemplate(name, template)

        return new ResponseEntity<TemplateResponse>(new TemplateResponse(template: newTemplate), HttpStatus.CREATED)
    }

    static class TemplateResponse {
        Template template
    }
}
