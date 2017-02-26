package com.insanedev.ping

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {

    @Value('${ping}')
    String ping

    @RequestMapping("/ping")
    String ping() {
        return this.ping
    }
}
