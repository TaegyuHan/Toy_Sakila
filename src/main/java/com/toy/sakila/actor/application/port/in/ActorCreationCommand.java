package com.toy.sakila.actor.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ActorCreationCommand {
    @NotBlank String firstName;
    @NotBlank String lastName;
}