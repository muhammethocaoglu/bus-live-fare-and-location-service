package com.casestudy.buslivefareandlocationservice.service;

import com.casestudy.buslivefareandlocationservice.model.BusFareResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@NoArgsConstructor
public class BusFareService {

    private final Map<Integer, Integer> busIdFareMap = Map.of(1, 101,
            2, 102,
            3, 103,
            4, 104,
            5, 105,
            6, 106,
            7, 107,
            8, 108,
            9, 109);

    private final AtomicBoolean returnFare = new AtomicBoolean(true);

    public Mono<BusFareResponse> getByBusId(Integer busId) {
        if (returnFare.get()) {
            returnFare.compareAndSet(true, false);
            return Mono.just(BusFareResponse.builder()
                    .id(busId)
                    .fare(busIdFareMap.get(busId))
                    .build());
        } else {
            returnFare.compareAndSet(false, true);
            return Mono.error(new RuntimeException("failed to retrieve bus fare"));
        }
    }
}
