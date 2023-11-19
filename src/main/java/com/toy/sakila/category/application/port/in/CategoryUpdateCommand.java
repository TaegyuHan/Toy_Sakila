package com.toy.sakila.category.application.port.in;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class CategoryUpdateCommand {
    @NotNull
    private final String name;
}