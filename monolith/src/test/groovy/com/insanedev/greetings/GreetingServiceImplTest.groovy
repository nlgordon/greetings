package com.insanedev.greetings

import com.insanedev.greetings.templates.Template
import com.insanedev.greetings.templates.TemplateService
import spock.lang.Specification

class GreetingServiceImplTest extends Specification {

    GreetingServiceImpl service
    Greeting input = new Greeting(id: UUID.randomUUID(), templateName: "test")

    def setup() {
        service = new GreetingServiceImpl()
        service.templateService = Mock(TemplateService) {
            getTemplate("test") >> new Template(name: "test", template: "test template")
        }
    }

    def "gets the template for the greeting from the template service"() {
        when:
        Greeting greeting = service.generateGreeting("test")

        then:
        greeting.greeting == "test template"
    }

    def "saveGreeting sets the id of the greeting"() {
        when:
        Greeting greeting = service.saveGreeting(input)

        then:
        greeting.id != null
    }

    def "saveGreeting sets the greeting of the greeting"() {
        when:
        Greeting greeting = service.saveGreeting(input)

        then:
        greeting.greeting == "test template"
    }

    def "saveGreeting saves the greeting to persistent-ish storage"() {
        when:
        service.saveGreeting(input)

        then:
        service.greetings[input.id] == input
    }

    def "getGreeting gets a greeting by uuid"() {
        setup:
        UUID uuid = UUID.randomUUID()
        def expected = new Greeting(id: uuid)
        service.greetings[uuid] = expected

        when:
        Greeting actual = service.getGreeting(uuid)

        then:
        actual == expected
    }

    def "deleteAllGreetings truncates all greetings"() {
        setup:
        UUID uuid = UUID.randomUUID()
        def expected = new Greeting(id: uuid)
        service.greetings[uuid] = expected

        when:
        service.deleteAllGreetings()

        then:
        service.greetings.size() == 0
    }

    def "getAllGreetings returns all greetings"() {
        setup:
        service.greetings[input.id] = input

        when:
        Map greetings = service.getAllGreetings()

        then:
        greetings[input.id] == input
        greetings.size() == 1
    }
}
