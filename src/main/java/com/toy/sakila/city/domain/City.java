package com.toy.sakila.city.domain;

import com.toy.sakila.country.domain.Country;
import lombok.*;

@Value
@Getter @Setter
@Builder
@RequiredArgsConstructor
public class City {

    private final CityId cityId;

    private final String city;

    private final Country country;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class CityId {
        private final Long value;
    }
}