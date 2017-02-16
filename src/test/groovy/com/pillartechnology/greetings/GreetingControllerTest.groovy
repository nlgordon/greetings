package com.pillartechnology.greetings

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class GreetingControllerTest extends Specification {

    GreetingController controller
    MockMvc mockMvc

    Greeting mockGreeting

    def setup() {
        mockGreeting = new Greeting(greeting: "hello world")
        controller = new GreetingController()
        controller.greetingService = Mock(GreetingService)
        controller.greetingService.generateGreeting(_) >> mockGreeting

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "responds to a ping request"() {
        when:
        String actual = controller.ping()

        then:
        actual == "pong"
    }

    def "when the ping api is called the status is 200/OK"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/ping"))

        then:
        result.andExpect(status().isOk())
    }

    def "when the ping api is called the return is 'pong'"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/ping"))

        then:
        result.andExpect(content().string("pong"))
    }

    def "gets its greetings from the GreetingService"() {
        when:
        controller.greeting("template")

        then:
        1 * controller.greetingService.generateGreeting(_) >> mockGreeting
    }

    def "returns a GreetingResponse object with the greeting populated"() {
        when:
        GreetingController.GreetingResponse response = controller.greeting("template")

        then:
        response.greeting == "hello world"
    }

    def "when the api for a greeting is called the status is 200/OK"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/greeting?template=foo"))

        then:
        result.andExpect(status().isOk())
    }

    def "when the api for a greeting is called, the response type is json"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/greeting?template=foo").accept(MediaType.APPLICATION_JSON_UTF8))

        then:
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }

    def "when the api for a greeting is called with a template parameter, it is passed to the greeting service"() {
        when:
        mockMvc.perform(get("/api/greeting?template=hello world").accept(MediaType.APPLICATION_JSON_UTF8))

        then:
        1 * controller.greetingService.generateGreeting("hello world") >> mockGreeting
    }

    def "when the api is called without a template parameter, a 400 status is returned"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/greeting"))

        then:
        result.andExpect(status().isBadRequest())
    }

    def "when the api is called the greeting service content is returned to the user"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/greeting?template=foo"))

        then:
        result.andExpect(content().json('{"greeting": "hello world"}'))
    }
}
