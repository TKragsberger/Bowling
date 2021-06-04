package controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import spock.lang.Specification

import static io.micronaut.http.HttpMethod.GET
import static io.micronaut.http.HttpRequest.create

class IntegrationHelper extends Specification {


    String getBaseUrl() {
        return "http://localhost:$serverPort/bowling"
    }

    httpGET() {
        return createHttpCall()
    }

    private HttpResponse<String> createHttpCall() {
        def request
        request = create(GET, baseUrl)
        return client.exchange(request, String)
    }

    private BlockingHttpClient getClient() {
        HttpClient.create("${baseUrl}".toURL()).toBlocking()
    }
}
