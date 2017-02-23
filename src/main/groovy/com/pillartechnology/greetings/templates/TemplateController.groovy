package com.pillartechnology.greetings.templates

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TemplateController {

    @Autowired
    TemplateService templateService

    @RequestMapping("/api/template/{name}")
    ResponseEntity<TemplateResponse> getTemplate(@PathVariable String name) {
        Template template = templateService.getTemplate(name)
        if (template == null) {
            return generateResponse(null, HttpStatus.NOT_FOUND)
        }

        return generateResponse(template, HttpStatus.OK)
    }

    @RequestMapping(path = "/api/template/{name}", method = [RequestMethod.POST])
    ResponseEntity<TemplateResponse> addTemplate(@PathVariable String name, @RequestParam String template) {
        Template newTemplate = templateService.addTemplate(name, template)

        return generateResponse(newTemplate, HttpStatus.CREATED)
    }

    @RequestMapping(path = "/api/template", method = [RequestMethod.POST])
    ResponseEntity<TemplateResponse> addTemplateObject(@RequestBody Template template) {
        template = templateService.addTemplate(template)

        return generateResponse(template, HttpStatus.CREATED)
    }

    ResponseEntity<TemplateResponse> generateResponse(Template template, HttpStatus status) {
        return new ResponseEntity<TemplateResponse>(new TemplateResponse(template: template), status)
    }

    static class TemplateResponse {
        Template template
    }
}
