package com.pillartechnology.greetings

import spock.lang.Specification

class GreetingControllerTest extends Specification {

    GreetingController controller

    def setup() {
        controller = new GreetingController()
        controller.greetingService = Mock(GreetingService)
    }

    def "GreetingController responds to a ping request"() {
        when:
        String actual = controller.ping()

        then:
        actual == "pong"
    }

    def "gets its greetings from the GreetingService"() {
        when:
        String actual = controller.greeting("template")

        then:
        1 * controller.greetingService.generateGreeting("template")
    }
}
