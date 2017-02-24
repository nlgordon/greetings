package com.insanedev.discovery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.netflix.eureka.server.EurekaController
import spock.lang.Specification

@SpringBootTest
class DiscoveryTest extends Specification {

    @Autowired
    EurekaController eurekaController

    def "eureka server is present"() {
        expect:
        eurekaController != null
    }
}
