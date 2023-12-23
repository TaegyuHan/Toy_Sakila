package com.toy.sakila.country.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class Country {
    private final CountryId id;
    private String country;
    private LocalDateTime lastUpdate;
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class CountryId {
        Short value;
    }
}