package com.casestudy.buslivefareandlocationservice.controller;

import com.casestudy.buslivefareandlocationservice.model.BusFareResponse;
import com.casestudy.buslivefareandlocationservice.service.BusFareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusFareController {

    private final BusFareService busFareService;

    @GetMapping(path = "/{id}/fare", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BusFareResponse> getByBusId(@PathVariable(value = "id") int busId) {
        return busFareService.getByBusId(busId);
    }

}