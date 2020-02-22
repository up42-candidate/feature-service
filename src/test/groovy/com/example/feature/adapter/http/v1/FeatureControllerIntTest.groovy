package com.example.feature.adapter.http.v1

import com.example.Application
import groovyx.net.http.HttpBuilder
import groovyx.net.http.NativeHandlers
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import spock.lang.Specification

import static groovyx.net.http.HttpBuilder.configure
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@SpringBootTest(classes = [Application], webEnvironment = RANDOM_PORT)
class FeatureControllerIntTest extends Specification {

    @Value('classpath:up42.png')
    private Resource up42Image

    @Value('${local.server.port}')
    private int port

    private HttpBuilder http

    def setup() {
        http = configure {
            request.uri = "http://localhost:$port"
            response.parser(APPLICATION_JSON_VALUE) { config, resp ->
                NativeHandlers.Parsers.json(config, resp)
            }
        }
    }

    def '/features/{id} should return feature'() {
        given:
        def id = '39c2f29e-c0f8-4a39-a98b-deed547d6aea'
        def path = "/features/$id"

        when:
        def result = http.get(Map.class) {
            request.uri.path = path
        }

        then:
        result == [
                id              : id,
                timestamp       : 1554831167697,
                beginViewingDate: 1554831167697,
                endViewingDate  : 1554831202043,
                missionName     : 'Sentinel-1'
        ]
    }

    def '/features/{id}/quicklook should return quicklook'() {
        given:
        def id = '39c2f29e-c0f8-4a39-a98b-deed547d6aea'
        def path = "/features/$id/quicklook"

        when:
        def result = http.get(byte[].class) {
            request.uri.path = path
        }

        then:
        up42Image.file.bytes == result
    }

}
