package com.toy.sakila.actor.application.port.in;

import com.toy.sakila.actor.domain.Actor;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ActorCommand {
    @NotBlank String firstName;
    @NotBlank String lastName;

    public Actor toDomainEntity() {
        return Actor.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}