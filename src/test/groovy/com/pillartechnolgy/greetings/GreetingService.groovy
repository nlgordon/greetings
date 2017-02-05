package com.pillartechnolgy.greetings

class GreetingService {
    String generateGreeting() {
        return "Hello World"
    }

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
