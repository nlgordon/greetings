package com.insanedev.gateway

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "Can ping the config api"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange("/info", HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
    }
}
