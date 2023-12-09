package com.toy.sakila.category.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class CategoryCreationCommand {
    private final @NotBlank String name;
}