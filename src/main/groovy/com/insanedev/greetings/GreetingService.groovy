package com.insanedev.greetings

interface GreetingService {
    Greeting generateGreeting(String template)

    Greeting saveGreeting(Greeting greeting)

    Greeting getGreeting(UUID uuid)
}
