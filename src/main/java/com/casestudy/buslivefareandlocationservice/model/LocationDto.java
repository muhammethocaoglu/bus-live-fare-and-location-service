package com.casestudy.buslivefareandlocationservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private int lat;
    private int lng;
}
