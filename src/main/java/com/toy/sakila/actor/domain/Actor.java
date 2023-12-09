package com.toy.sakila.actor.domain;

import com.toy.sakila.common.domain.BaseDomain;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class Actor extends BaseDomain {

    private final ActorId id;
    private String lastName;
    private String firstName;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class ActorId {
        private final Long value;
    }
}