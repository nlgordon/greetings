package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.Template
import com.pillartechnology.greetings.templates.TemplateService
import spock.lang.Specification

class GreetingServiceImplTest extends Specification {

    GreetingServiceImpl service

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
        setup:
        Greeting input = new Greeting(templateName: "test")

        when:
        Greeting greeting = service.saveGreeting(input)

        then:
        greeting.id != null
    }

    def "saveGreeting sets the greeting of the greeting"() {
        setup:
        Greeting input = new Greeting(templateName: "test")

        when:
        Greeting greeting = service.saveGreeting(input)

        then:
        greeting.greeting == "test template"
    }
}
