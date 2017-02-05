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
        greeting == "Hello World"
    }

    def "greeting service allows you to retrieve the 'hello world' greeting"() {
        when:
        String greeting = service.generateGreeting("hello world")

        then:
        greeting == "Hello World"
    }

    def "greeting service allows you to retrieve the 'valentines' greeting"() {
        when:
        String greeting = service.generateGreeting("valentines")

        then:
        greeting == "Happy Valentines!"
    }

    def "not specifying a template throws an exception"() {
        when:
        String greeting = service.generateGreeting("")

        then:
        IllegalArgumentException e = thrown()
        e.cause == null
        greeting == null
    }
}
