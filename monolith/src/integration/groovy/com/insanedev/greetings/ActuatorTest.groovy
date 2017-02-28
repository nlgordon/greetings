package com.insanedev.greetings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorTest extends Specification {
    @Autowired
    TestRestTemplate restTemplate

    def "actuator health responds"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange("/health", HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "env endpoint is not secured"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange("/env", HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
    }
}
