package com.toy.sakila.language.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class LanguageUpdateCommand {
    @NotBlank
    private final String name;
}