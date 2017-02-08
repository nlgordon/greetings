package com.pillartechnology.greetings.templates

import spock.lang.Specification

class TemplateServiceImplTest extends Specification {

    def "the template service returns 'hello world' for the 'hello' template"() {
        setup:
        TemplateServiceImpl service = new TemplateServiceImpl();

        when:
        String template = service.getTemplate('hello')

        then:
        template == 'hello world'
    }
}
