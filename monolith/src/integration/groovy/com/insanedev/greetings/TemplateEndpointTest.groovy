package com.insanedev.greetings

import com.insanedev.greetings.templates.TemplateResponse
import com.insanedev.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemplateEndpointTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    TemplateService templateService

    def setup() {
        templateService.truncateTemplates()
    }

    def teardown() {
        templateService.truncateTemplates()
    }

    def "can get a template"() {
        setup:
        templateService.addTemplate("test", "hello world")

        when:
        TemplateResponse response = restTemplate.getForObject("/api/template/test", TemplateResponse)

        then:
        response.template.name == "test"
    }
}
