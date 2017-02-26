package com.insanedev.ping

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
