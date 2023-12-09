package com.toy.sakila.country.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class CountryUpdateCommand {
    @NotBlank
    private final String country;
}