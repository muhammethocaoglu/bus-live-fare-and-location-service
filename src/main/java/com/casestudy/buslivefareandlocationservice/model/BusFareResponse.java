package com.casestudy.buslivefareandlocationservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusFareResponse {
    private Integer id;
    private Integer fare;
}
