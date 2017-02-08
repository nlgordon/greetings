package com.pillartechnology.greetings

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    GreetingService greetingService

    String ping() {
        return "pong"
    }

    @RequestMapping("/api/greeting")
    GreetingResponse greeting(@RequestParam(name = "template") String template) {
        return new GreetingResponse(greeting: greetingService.generateGreeting(template))
    }

    static class GreetingResponse {
        String greeting
    }
}
