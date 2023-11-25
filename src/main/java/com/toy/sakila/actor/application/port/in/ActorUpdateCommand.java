package com.toy.sakila.actor.application.port.in;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class ActorUpdateCommand {
    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;
}