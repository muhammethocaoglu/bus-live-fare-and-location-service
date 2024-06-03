package com.casestudy.buslivefareandlocationservice.controller;

import com.casestudy.buslivefareandlocationservice.service.LocationStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/location-stream")
@RequiredArgsConstructor
public class LocationStreamController {

    private final LocationStreamService locationStreamService;

    @GetMapping(path = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> retrieve() {
        return locationStreamService.retrieve();
    }

}