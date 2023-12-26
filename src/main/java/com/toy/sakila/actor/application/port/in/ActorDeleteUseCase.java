package com.toy.sakila.actor.application.port.in;

import com.toy.sakila.actor.domain.Actor;

public interface ActorDeleteUseCase {
    void delete(Actor.ActorId id);
}
