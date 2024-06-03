package com.casestudy.buslivefareandlocationservice.controller;

import com.casestudy.buslivefareandlocationservice.model.LocationDto;
import com.casestudy.buslivefareandlocationservice.model.LocationResponse;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ratpack.stream.Streams;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@RestController
@RequestMapping("/location-stream")
public class LocationStreamController {

    @GetMapping(path = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> retrieve() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Publisher<Object> locationPublisher = Streams
                .periodically(executor,
                        Duration.ofSeconds(1),
                        (t) -> t != 0 && t % 5 == 0 ?
                                new RuntimeException("failure") :
                                LocationResponse.builder()
                                        .id(new Random().nextInt(100))
                                        .location(LocationDto.builder()
                                                .lat(new Random().nextInt(90))
                                                .lng(new Random().nextInt(180))
                                                .build())
                                        .build()
                );

        return Flux.from(locationPublisher)
                .flatMap(object -> object instanceof Throwable ?
                        Flux.error((Throwable) object) :
                        Flux.just(object));

    }

}