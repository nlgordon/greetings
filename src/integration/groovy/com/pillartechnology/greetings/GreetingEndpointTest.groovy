package com.pillartechnology.greetings

import org.springframework.beans.factory.annotation.Autowired
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

    def "context starts with a port"() {
        expect:
        port != 0
    }

    def "ping responds"() {
        when:
        String content = restTemplate.getForObject("/api/ping", String)

        then:
        content == "pong"
    }
}
