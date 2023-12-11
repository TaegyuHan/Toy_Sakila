package com.toy.sakila.category.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class CategoryUpdateCommand {
    @NotBlank String name;
}