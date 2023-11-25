package com.toy.sakila.actor.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class Actor {

    private final ActorId id;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime lastUpdate;

    @Value
    @AllArgsConstructor
    public static class ActorId {
        private final Long value;
    }

}