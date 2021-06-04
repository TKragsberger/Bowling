package controller

import bowling.BowlingController
import bowling.SkatService
import grails.testing.mixin.integration.Integration
import io.micronaut.http.HttpResponse

import javax.inject.Inject

import static io.micronaut.http.HttpStatus.OK

@Integration
class BowlingControllerRestSpec extends IntegrationHelper {

    @Inject
    BowlingController bowlingController


    def setup() {
        String token = "pRQfEZtV8P5WQf3dHgHxo6FxUTuZIsJo"
        List<Integer> points = [[5, 2]]
        def json = '{"token": "' + token + '", "points": ' + points + '}'
        HttpResponse httpResponseGET = Mock(HttpResponse.class) {
            getBody() >> { return new Optional<>(json) }
        }
        HttpResponse httpResponsePOST = Mock(HttpResponse.class) {
            status() >> { return OK }
        }
        bowlingController.skatService = Mock(SkatService) {
            getPointsFromServer() >> { return httpResponseGET }
            postPointsToServer(_) >> { return httpResponsePOST }
        }
    }

    def "calling /bowling endpoint skatservice mocked"() {
        when: "calling the /bowling endpoint"
            def response = httpGET()
        then:
            response.status == OK
    }

}
