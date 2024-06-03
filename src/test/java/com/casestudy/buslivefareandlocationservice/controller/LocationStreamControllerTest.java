package com.casestudy.buslivefareandlocationservice.controller;

import com.casestudy.buslivefareandlocationservice.service.LocationStreamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest(controllers = LocationStreamController.class)
@Import({LocationStreamService.class})
public class LocationStreamControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void test_should_return_location_stream_when_retrieve() {
        FluxExchangeResult<Object> result = webClient
                .get()
                .uri("/location-stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .returnResult(Object.class);

        Flux<Object> locationStream = result.getResponseBody();

        StepVerifier.create(locationStream)
                .expectSubscription()
                .expectNextCount(5)
                .expectError()
                .verify();

    }
}