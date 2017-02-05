package com.pillartechnolgy.greetings

import spock.lang.Specification

class GreetingServiceTest extends Specification {

    GreetingService service;

    def setup() {
       service = new GreetingService();
    }

    def "greeting service returns a simple greeting"() {
        when:
        String greeting = service.generateGreeting()

        then:
        greeting == 'Hello World'
    }

    def "greeting service allows you to specify a desired greeting"() {
        when:
        String greeting = service.generateGreeting("hello world")

        then:
        greeting == 'Hello World'
    }
}
