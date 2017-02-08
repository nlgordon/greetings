package com.pillartechnology.greetings

import org.springframework.stereotype.Component

@Component
class GreetingServiceImpl implements GreetingService {

    String generateGreeting() {
        return "Hello World"
    }

    @Override
    String generateGreeting(String template) {

        if (template == "") {
            throw new IllegalArgumentException("Template must be specified")
        }

        if (template == "valentines") {
            return "Happy Valentines!"
        } else if (template == "hello world") {
            return "Hello World"
        }
    }
}
