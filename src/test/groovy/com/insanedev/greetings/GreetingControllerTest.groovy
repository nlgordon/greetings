package com.insanedev.greetings

import com.google.gson.Gson
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class GreetingControllerTest extends Specification {

    GreetingController controller
    MockMvc mockMvc

    Greeting mockGreeting
    Gson gson

    def setup() {
        mockGreeting = new Greeting(greeting: "hello world")
        controller = new GreetingController()
        controller.greetingService = Mock(GreetingService)
        controller.greetingService.generateGreeting(_) >> mockGreeting
        controller.greetingService.saveGreeting(_) >> { Greeting greeting -> return greeting }

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        gson = new Gson()
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
        GreetingResponse response = controller.greeting("template")

        then:
        response.greeting.greeting == "hello world"
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
        result.andExpect(jsonPath('$.greeting.greeting', is("hello world")))
    }

    def "posts to the greeting endpoint return status created"() {
        setup:
        Greeting request = new Greeting(templateName: "test")

        when:
        ResultActions result = postGreetingRequest(request)

        then:
        result.andExpect(status().isCreated())
    }

    def "posts to the greeting endpoint generate a greeting object"() {
        setup:
        Greeting request = new Greeting(templateName: "test")

        when:
        ResultActions result = postGreetingRequest(request)

        then:
        result.andExpect(jsonPath('$.greeting.templateName', is("test")))
    }

    def "posts to the greeting endpoint save to the service"() {
        setup:
        Greeting request = new Greeting(templateName: "test")

        when:
        postGreetingRequest(request)

        then:
        1 * controller.greetingService.saveGreeting(_) >> { Greeting greeting ->
            greeting.greeting = "hello world"
            return greeting
        }
    }

    def "gets to a uuid url return 200 OK"() {
        setup:
        UUID uuid = UUID.randomUUID()
        Greeting expected = new Greeting(id: uuid)
        controller.greetingService.getGreeting(uuid) >> expected

        when:
        ResultActions result = getGreetingResponse(uuid)

        then:
        result.andExpect(status().isOk())
    }

    def "gets to a uuid url return a greeting response"() {
        setup:
        UUID uuid = UUID.randomUUID()
        Greeting expected = new Greeting(id: uuid)
        controller.greetingService.getGreeting(uuid) >> expected

        when:
        ResultActions result = getGreetingResponse(uuid)

        then:
        result.andExpect(jsonPath('$.greeting.id', is(uuid.toString())))
    }

    def "gets to a uuid url of unknown greeting return 404"() {
        setup:
        UUID uuid = UUID.randomUUID()

        when:
        ResultActions result = getGreetingResponse(uuid)

        then:
        result.andExpect(status().isNotFound())
    }

    ResultActions postGreetingRequest(Greeting request) {
        return mockMvc.perform(post("/api/greeting")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(request)))
    }

    ResultActions getGreetingResponse(UUID uuid) {
        return mockMvc.perform(get("/api/greeting/$uuid").accept(MediaType.APPLICATION_JSON_UTF8))
    }
}
