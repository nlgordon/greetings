package com.insanedev.greetings

import com.insanedev.greetings.templates.TemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingEndpointTest extends Specification {

    @LocalServerPort
    int port

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    TemplateService templateService

    @Value('${pingBaseUrl}')
    String pingBaseUrl

    @Value('${greetingsBaseUrl}')
    String greetingsBaseUrl

    def setup() {
        templateService.addTemplate("test", "hello world")
    }

    def teardown() {
        templateService.truncateTemplates()
    }

    def "context starts with a port"() {
        expect:
        port != 0
    }

    def "ping responds"() {
        when:
        String content = restTemplate.getForObject(pingBaseUrl, String)

        then:
        content == "pong"
    }

    def "transient greeting endpoint responds"() {
        when:
        GreetingResponse response = restTemplate.getForObject("$greetingsBaseUrl/transient?template=test", GreetingResponse)

        then:
        response.greeting.greeting == "hello world"
    }
}
