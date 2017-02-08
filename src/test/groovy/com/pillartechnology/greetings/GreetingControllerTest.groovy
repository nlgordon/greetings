package com.pillartechnology.greetings

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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

    def "when the api for a greeting is called, the greeting service is invoked"() {
        setup:
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        when:
        ResultActions result = mockMvc.perform(get("/api/greeting"))

        then:
        result.andExpect(status().isOk())
    }
}
