package com.toy.sakila.actor.application.port.in;

import com.toy.sakila.actor.domain.Actor;


public interface ActorUpdateUseCase {
    Actor update(Actor.ActorId id, ActorCommand command);
}