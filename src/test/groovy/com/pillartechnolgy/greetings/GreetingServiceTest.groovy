package com.pillartechnolgy.greetings

import spock.lang.Specification

class GreetingServiceTest extends Specification {

    def "greeting service returns a simple greeting"() {
        setup:
        GreetingService service = new GreetingService();

        when:
        String greeting = service.generateGreeting();

        then:
        greeting == 'Hello World'
    }
}
