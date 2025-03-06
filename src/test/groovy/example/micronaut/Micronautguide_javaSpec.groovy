package example.micronaut

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest(transactional = false)
class Micronautguide_javaSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    @Inject
    FunctionRequestHandler functionRequestHandler

    void 'test it works'() {
        expect:
        application.running
    }

}
