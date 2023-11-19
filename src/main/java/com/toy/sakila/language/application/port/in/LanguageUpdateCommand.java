package com.toy.sakila.language.application.port.in;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class LanguageUpdateCommand {
    @NotNull
    private final String name;
}