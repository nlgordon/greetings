package com.pillartechnology.greetings

import com.pillartechnology.greetings.templates.TemplateController
import com.pillartechnology.greetings.templates.TemplateServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class ApplicationTest extends Specification {

    @Autowired
    ApplicationContext context

    def "application context loads correctly"() {
        expect:
        context != null
    }

    def "context contains a greeting service"() {
        when:
        GreetingService service = context.getBean(GreetingService)

        then:
        service != null
    }

    def "context contains the greetings controller"() {
        when:
        GreetingController controller = context.getBean(GreetingController)

        then:
        controller != null
    }

    def "greeting controller has the greeting service wired into it"() {
        when:
        GreetingController controller = context.getBean(GreetingController)

        then:
        controller.greetingService != null
    }

    def "greeting service has the template service wired into it"() {
        when:
        GreetingServiceImpl service = context.getBean(GreetingService)

        then:
        service.templateService != null
    }

    def "template controller has the template service wired into it"() {
        when:
        TemplateController controller = context.getBean(TemplateController)

        then:
        controller.templateService != null
    }
}
