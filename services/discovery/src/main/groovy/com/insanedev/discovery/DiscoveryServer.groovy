package com.insanedev.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryServer {
    static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryServer).web(true).run(args)
    }
}
