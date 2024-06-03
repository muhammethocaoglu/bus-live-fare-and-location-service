package com.casestudy.buslivefareandlocationservice.controller;

import com.casestudy.buslivefareandlocationservice.service.BusFareService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = BusFareController.class)
@Import({BusFareService.class})
public class BusFareControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void test_should_return_bus_fare_and_error_periodically_when_get_by_busId() {
        int busId = 1;
        webClient
                .get()
                .uri(String.format("/buses/%d/fare", busId))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(busId)
                .jsonPath("$.fare").isEqualTo(101);

        webClient
                .get()
                .uri(String.format("/buses/%d/fare", busId))
                .exchange()
                .expectStatus().is5xxServerError();

        webClient
                .get()
                .uri(String.format("/buses/%d/fare", busId))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(busId)
                .jsonPath("$.fare").isEqualTo(101);

    }
}