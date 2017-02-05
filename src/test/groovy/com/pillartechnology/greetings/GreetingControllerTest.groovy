package com.pillartechnology.greetings

import spock.lang.Specification

class GreetingControllerTest extends Specification {

    GreetingController controller

    def setup() {
        controller = new GreetingController()
    }

    def "GreetingController responds to a ping request"() {
        when:
        String actual = controller.ping()

        then:
        actual == "pong"
    }

    def "GreetingController responds to greeting requests"() {
        when:
        String actual = controller.greeting()

        then:
        actual == "greeting"
    }
}
