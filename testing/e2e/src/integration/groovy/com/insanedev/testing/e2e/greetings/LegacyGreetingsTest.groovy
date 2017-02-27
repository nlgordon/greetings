package com.insanedev.testing.e2e.greetings

import com.insanedev.greetings.templates.Template
import com.insanedev.testing.e2e.TemplateHelperService
import com.insanedev.greetings.Greeting
import com.insanedev.greetings.GreetingResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LegacyGreetingsTest extends Specification {

    @Autowired
    RestTemplate restTemplate

    @Autowired
    TemplateHelperService templateService

    @Value('${pingUrl}')
    String pingUrl

    @Value('${greetingUrl}')
    String greetingUrl

    @Value('${templateUrl}')
    String templateUrl

    Template testTemplate = new Template(name: "test", template: "test template")

    def setup() {
        templateService.createTemplate(testTemplate)
    }

    def cleanup() {
        templateService.deleteTemplates()
    }

    def "able to ping the api"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange(pingUrl, HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "ping responds with pong"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange(pingUrl, HttpMethod.GET, null, String)

        then:
        response.body == "pong"
    }

    def "can create a transient greeting"() {
        when:
        ResponseEntity<GreetingResponse> response = restTemplate.exchange(greetingUrl + "?template=test", HttpMethod.GET, null, GreetingResponse)

        then:
        response.statusCode == HttpStatus.OK
        response.body.greeting.greeting == testTemplate.template
    }

    def "can create a persistent greeting"() {
        setup:
        HttpEntity<Greeting> request = new HttpEntity<>(new Greeting(templateName: testTemplate.name))

        when:
        ResponseEntity<GreetingResponse> response = restTemplate.exchange(greetingUrl, HttpMethod.POST, request, GreetingResponse)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body.greeting.id != null
        response.body.greeting.greeting == testTemplate.template
    }
}
