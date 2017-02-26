package com.insanedev.discovery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.cloud.netflix.eureka.server.EurekaController
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiscoveryTest extends Specification {

    @Autowired
    EurekaController eurekaController

    @Autowired
    TestRestTemplate restTemplate

    def "eureka server is present"() {
        expect:
        eurekaController != null
    }

    def "actuator health responds"() {
        when:
        ResponseEntity<String> response = restTemplate.exchange("/health", HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
    }
}
