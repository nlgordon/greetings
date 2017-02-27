package com.insanedev.greetings.templates

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TemplateControllerTest extends Specification {
    TemplateController controller
    MockMvc mockMvc

    def setup() {
        controller = new TemplateController()
        controller.templateService = Mock(TemplateService) {
            getTemplate("test") >> new Template(name: "test", template: "test template")
            addTemplate(_, _) >> { args -> return new Template(name: args[0], template: args[1]) }
            getAllTemplates() >> ["test": new Template(name: "test", template: "test template")]
        }

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "allows asking for a template from the template service"() {
        when:
        ResponseEntity<TemplateResponse> response = controller.getTemplate("test")

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
        ResultActions result = postNewTemplate()

        then:
        result.andExpect(status().isCreated())
    }

    def "posting a template returns the created template with the name from the url"() {
        when:
        ResultActions result = postNewTemplate()

        then:
        result.andExpect(jsonPath('$.template.name', is("foo")))
    }

    def "posting a template returns the created template with the template based on the form param"() {
        when:
        ResultActions result = postNewTemplate()

        then:
        result.andExpect(jsonPath('$.template.template', is("my foo template")))
    }

    def "a posted template saves it to the service"() {
        when:
        postNewTemplate()

        then:
        1 * controller.templateService.addTemplate("foo", "my foo template")
    }

    def "a posted template object saves to the service"() {
        when:
        mockMvc.perform(post("/api/template")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content('{"name": "test", "template": "My Test Template"}'))

        then:
        1 * controller.templateService.addTemplate(_)
    }

    def "can get all templates"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/template"))

        then:
        result.andExpect(status().isOk())
    }

    def "getting all templates returns the test template"() {
        when:
        ResultActions result = mockMvc.perform(get("/api/template"))

        then:
        result.andExpect(jsonPath('$.test.name', is("test")))
    }

    def "can truncate all templates"() {
        when:
        ResultActions result = mockMvc.perform(delete("/api/template"))

        then:
        result.andExpect(status().isOk())
    }

    ResultActions postNewTemplate() {
        return mockMvc.perform(post("/api/template/foo").param("template", "my foo template"))
    }
}
