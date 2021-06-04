package bowling

import grails.core.GrailsApplication
import groovy.json.JsonSlurper
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient

import static io.micronaut.http.HttpMethod.GET
import static io.micronaut.http.HttpMethod.POST
import static io.micronaut.http.HttpRequest.create

class SkatService {
    GrailsApplication grailsApplication
    private static final SKAT_URL = 'skat.api.url'

    HttpResponse<String> getPointsFromServer() {
        return createHttpCall(GET)
    }

    HttpResponse<String> postPointsToServer(body) {
        return createHttpCall(POST, body)
    }

    String getBaseUrl() {
        return grailsApplication.config.getProperty(SKAT_URL)
    }

    BlockingHttpClient getClient() {
        HttpClient.create("${getBaseUrl()}".toURL()).toBlocking()
    }


    private HttpResponse<String> createHttpCall(HttpMethod method, def body = null) {
        def request
        if (body) {
            request = create(method, baseUrl).body(body).contentType("Content-Type:application/json")
            return client.exchange(request, String)
        }
        request = create(method, baseUrl)
        return client.exchange(request, String)
    }

}
