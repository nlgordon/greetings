package com.pillartechnology.greetings

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    GreetingService greetingService

    String ping() {
        return "pong"
    }

    @RequestMapping("/api/greeting")
    String greeting(String template) {
        return greetingService.generateGreeting(template)
    }
}
