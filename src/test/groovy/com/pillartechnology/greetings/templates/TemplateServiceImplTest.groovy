package com.pillartechnology.greetings.templates

import spock.lang.Specification

class TemplateServiceImplTest extends Specification {

    TemplateServiceImpl service

    def setup() {
        service = new TemplateServiceImpl()
    }

    def "the template service returns 'hello world' for the 'hello' template"() {
        when:
        String template = service.getTemplate('hello')

        then:
        template == 'hello world'
    }

    def "the template service returns a greeting for valentines day"() {
        when:
        String template = service.getTemplate('valentines')

        then:
        template == 'Happy Valentines Day!'
    }

    def "if a template isn't specified, an error is thrown"() {
        when:
        service.getTemplate("")

        then:
        thrown(IllegalArgumentException)
    }

    def "if a template isn't known, throw an error"() {
        when:
        service.getTemplate("unknown template")

        then:
        thrown(IllegalArgumentException)
    }
}
