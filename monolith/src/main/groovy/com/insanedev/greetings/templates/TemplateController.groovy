package com.insanedev.greetings.templates

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('${templatesBaseUrl}')
class TemplateController {

    @Autowired
    TemplateService templateService

    @RequestMapping(method = [RequestMethod.GET])
    ResponseEntity<Map<String, Template>> getAllTemplates() {
        return new ResponseEntity<Map<String,Template>>(templateService.getAllTemplates(), HttpStatus.OK)
    }

    @RequestMapping(method = [RequestMethod.DELETE])
    ResponseEntity<String> deleteAllTemplates() {
        templateService.getAllTemplates()
        return new ResponseEntity<String>("templates deleted", HttpStatus.OK)
    }

    @RequestMapping('/{name}')
    ResponseEntity<TemplateResponse> getTemplate(@PathVariable String name) {
        Template template = templateService.getTemplate(name)
        if (template == null) {
            return generateResponse(null, HttpStatus.NOT_FOUND)
        }

        return generateResponse(template, HttpStatus.OK)
    }

    @RequestMapping(path = '/{name}', method = [RequestMethod.POST])
    ResponseEntity<TemplateResponse> addTemplate(@PathVariable String name, @RequestParam String template) {
        Template newTemplate = templateService.addTemplate(name, template)

        return generateResponse(newTemplate, HttpStatus.CREATED)
    }

    @RequestMapping(method = [RequestMethod.POST])
    ResponseEntity<TemplateResponse> addTemplateObject(@RequestBody Template template) {
        template = templateService.addTemplate(template)

        return generateResponse(template, HttpStatus.CREATED)
    }

    ResponseEntity<TemplateResponse> generateResponse(Template template, HttpStatus status) {
        return new ResponseEntity<TemplateResponse>(new TemplateResponse(template: template), status)
    }
}
