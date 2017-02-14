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
        String greeting = service.generateGreeting("test")

        then:
        greeting == "test template"
    }
}
