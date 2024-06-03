package com.casestudy.buslivefareandlocationservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusFareResponse {
    private int id;
    private Integer fare;
}
