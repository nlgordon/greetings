package com.insanedev.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
