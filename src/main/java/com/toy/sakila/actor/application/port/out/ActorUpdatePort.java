package com.toy.sakila.actor.application.port.out;

import com.toy.sakila.actor.domain.Actor;

public interface ActorUpdatePort {
    Actor update(Actor actor);
}