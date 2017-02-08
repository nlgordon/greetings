package com.pillartechnology.greetings.templates

import spock.lang.Specification

class TemplateControllerTest extends Specification {

    def "allows asking for a template"() {
        setup:
        TemplateController controller = new TemplateController()

        when:
        TemplateController.TemplateResponse template = controller.getTemplate("test")

        then:
        template.template == "test template"
    }
}
