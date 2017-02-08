package com.pillartechnology.greetings.templates

import spock.lang.Specification

class TemplateControllerTest extends Specification {
    TemplateController controller

    def setup() {
        controller = new TemplateController()
        controller.templateService = Mock(TemplateService) {
            getTemplate("test") >> "test template"
        }
    }

    def "allows asking for a template from the template service"() {
        when:
        TemplateController.TemplateResponse template = controller.getTemplate("test")

        then:
        template.template == "test template"
    }
}
