package com.pillartechnology.greetings.templates

import spock.lang.Specification

class TemplateServiceImplTest extends Specification {

    TemplateServiceImpl service

    def setup() {
        service = new TemplateServiceImpl()
        service.templates = [
                'hello': new Template(name: 'hello', template: 'hello world'),
                'valentines': new Template(name: 'valentines', template: 'Happy Valentines Day!')
        ]
    }

    def "the template service returns 'hello world' for the 'hello' template"() {
        when:
        Template template = service.getTemplate('hello')

        then:
        template.template == 'hello world'
    }

    def "the template service returns a greeting for valentines day"() {
        when:
        Template template = service.getTemplate('valentines')

        then:
        template.template == 'Happy Valentines Day!'
    }

    def "if a template isn't specified, an error is thrown"() {
        when:
        service.getTemplate("")

        then:
        thrown(IllegalArgumentException)
    }

    def "if a template isn't known, return null"() {
        when:
        Template template = service.getTemplate("unknown template")

        then:
        template == null
    }

    def "can store templates"() {
        when:
        service.addTemplate("test", "test template")

        Template template = service.getTemplate("test")

        then:
        template.template == "test template"
    }

    def "can report if a template doesn't exist already"() {
        when:
        boolean hasTemplate = service.hasTemplate("test")

        then:
        hasTemplate == false
    }

    def "can report if a template does exist already"() {
        when:
        service.addTemplate("test", "test template")
        boolean hasTemplate = service.hasTemplate("test")

        then:
        hasTemplate == true
    }

    def "can add a template object directly"() {
        setup:
        Template input = new Template(name: "test template", template: "My Test Template")

        when:
        service.addTemplate(input)

        Template template = service.getTemplate("test template")

        then:
        template == input
    }

    def "when adding a template object, it must have a name"() {
        setup:
        Template input = new Template(template: "My Test Template")

        when:
        service.addTemplate(input)

        then:
        thrown(IllegalArgumentException)
    }
}
