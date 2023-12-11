package com.toy.sakila.city.domain;

import com.toy.sakila.country.domain.Country;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class City {

    private final CityId cityId;
    private String city;
    private Country country;
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class CityId {
        Long value;
    }
}