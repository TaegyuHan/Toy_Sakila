package com.toy.sakila.country.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Getter
@Builder
@RequiredArgsConstructor
public class Country {

    private CountryId id;

    @NonNull
    private final String country;
    private final LocalDateTime lastUpdate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class CountryId {
        private final Integer value;
    }
}