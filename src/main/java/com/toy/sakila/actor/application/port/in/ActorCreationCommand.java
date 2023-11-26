package com.toy.sakila.actor.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class ActorCreationCommand {
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
}