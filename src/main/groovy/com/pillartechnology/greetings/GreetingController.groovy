package com.pillartechnology.greetings

class GreetingController {

    GreetingService greetingService

    String ping() {
        return "pong"
    }

    String greeting(String template) {
        return greetingService.generateGreeting(template)
    }
}
