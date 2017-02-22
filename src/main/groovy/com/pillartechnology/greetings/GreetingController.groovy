package com.pillartechnology.greetings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        return generateResponse(greeting, HttpStatus.CREATED)
    }

    @RequestMapping(path = "/api/greeting/{uuid}", method = [RequestMethod.GET])
    ResponseEntity<GreetingResponse> getGreeting(@PathVariable UUID uuid) {
        Greeting greeting = greetingService.getGreeting(uuid)
        if (!greeting) {
            return generateResponse(null, HttpStatus.NOT_FOUND)
        }
        return generateResponse(greeting, HttpStatus.OK)
    }

    ResponseEntity<GreetingResponse> generateResponse(Greeting greeting, HttpStatus status) {
        return new ResponseEntity<GreetingResponse>(new GreetingResponse(greeting: greeting), status)
    }

    static class GreetingResponse {
        Greeting greeting
    }
}
