package com.insanedev.testing.e2e.templates

import com.insanedev.greetings.templates.Template
import com.insanedev.greetings.templates.TemplateResponse
import com.insanedev.testing.e2e.TemplateHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LegacyTemplatesTest extends Specification {

    @Autowired
    RestTemplate restTemplate

    @Autowired
    TemplateHelperService templateService

    @Value('${templateUrl}')
    String urlBase

    Template testTemplate = new Template(name: "test", template: "test template")

    def cleanup() {
        templateService.deleteTemplates()
    }

    def "can get all templates"() {
        when:
        ResponseEntity<Map> response = restTemplate.exchange(urlBase, HttpMethod.GET, null, Map)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "can clear all templates"() {
        when:
        ResponseEntity<String> response = templateService.deleteTemplates()

        then:
        response.statusCode == HttpStatus.OK
    }

    def "can create a template"() {
        when:
        ResponseEntity<TemplateResponse> response = templateService.createTemplate(testTemplate)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body.template == testTemplate
    }

    def "can get a template"() {
        setup:
        templateService.createTemplate(testTemplate)

        when:
        ResponseEntity<TemplateResponse> response = restTemplate.exchange("$urlBase/test", HttpMethod.GET, null, TemplateResponse)

        then:
        response.statusCode == HttpStatus.OK
        response.body.template == testTemplate
    }
}
