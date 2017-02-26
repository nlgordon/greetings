package com.insanedev.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigServer {
    static void main(String[] args) {
        SpringApplication.run(ConfigServer, args)
    }
}
