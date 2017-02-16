package com.pillartechnology.greetings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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

    @RequestMapping(path = "/api/greeting", method = [RequestMethod.GET])
    GreetingResponse greeting(@RequestParam(name = "template") String template) {
        Greeting greeting = greetingService.generateGreeting(template)
        return new GreetingResponse(greeting: greeting)
    }

    @RequestMapping(path = "/api/greeting", method = [RequestMethod.POST])
    ResponseEntity<GreetingResponse> saveGreeting(@RequestBody Greeting greeting) {
        greeting = greetingService.saveGreeting(greeting)
        return new ResponseEntity<GreetingResponse>(new GreetingResponse(greeting: greeting), HttpStatus.CREATED)
    }

    static class GreetingResponse {
        Greeting greeting
    }
}
