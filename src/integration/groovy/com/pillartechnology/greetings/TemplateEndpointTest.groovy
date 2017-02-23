package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.TemplateController
import com.pillartechnology.greetings.templates.TemplateServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemplateEndpointTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    TemplateServiceImpl templateService

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
        TemplateController.TemplateResponse response = restTemplate.getForObject("/api/template/test", TemplateController.TemplateResponse)

        then:
        response.template.name == "test"
    }
}
