package com.pillartechnology.greetings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @Autowired
    GreetingService greetingService

    @RequestMapping("/api/ping")
    String ping() {
        return "pong"
    }

    @RequestMapping("/api/greeting")
    GreetingResponse greeting(@RequestParam(name = "template") String template) {
        Greeting greeting = greetingService.generateGreeting(template)
        return new GreetingResponse(greeting: greeting.greeting)
    }

    static class GreetingResponse {
        String greeting
    }
}
