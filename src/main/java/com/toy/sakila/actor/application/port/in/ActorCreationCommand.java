package com.toy.sakila.actor.application.port.in;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class ActorCreationCommand {
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
}