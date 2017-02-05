package com.pillartechnolgy.greetings

class GreetingService {
    String generateGreeting() {
        return "Hello World"
    }

    String generateGreeting(String template) {

        if (template == "valentines") {
            return "Happy Valentines!"
        }
        return "Hello World"
    }
}
