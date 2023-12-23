package com.toy.sakila.actor.domain;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Actor {

    private final ActorId id;
    private String lastName;
    private String firstName;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class ActorId {
        Short value;
    }
}