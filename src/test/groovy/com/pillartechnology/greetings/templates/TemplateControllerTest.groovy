package com.pillartechnology.greetings.templates

import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class TemplateControllerTest extends Specification {
    TemplateController controller
    MockMvc mockMvc

    def setup() {
        controller = new TemplateController()
        controller.templateService = Mock(TemplateService) {
            getTemplate("test") >> new Template(name: "test", template: "test template")
            addTemplate(_, _) >> {args -> return new Template(name: args[0], template: args[1]) }
        }

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "allows asking for a template from the template service"() {
        when:
        ResponseEntity<TemplateController.TemplateResponse> response = controller.getTemplate("test")

        then:
        response.body.template.template == "test template"
    }

    def "returns a status 200/OK when a template is requested that exists"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/template/test"))

        then:
        result.andExpect(status().isOk())
    }

    def "returns a 404 when a template is requested that does not exist"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/template/foo"))

        then:
        result.andExpect(status().isNotFound())
    }

    def "allows posting a template"() {
        when:
        ResultActions result = mockMvc.perform(post("/api/template/foo").param("template", "my foo template"))

        then:
        result.andExpect(status().isCreated())
    }

    def "posting a template returns the created template"() {
        when:
        ResultActions result = mockMvc.perform(post("/api/template/foo").param("template", "my foo template"))

        then:
        result.andExpect(content().json('{"template": {"name": "foo", "template": "my foo template"}}'))
    }

    def "a posted template saves it to the service"() {
        when:
        mockMvc.perform(post("/api/template/foo").param("template", "my foo template"))

        then:
        1 * controller.templateService.addTemplate("foo", "my foo template")
    }
}
