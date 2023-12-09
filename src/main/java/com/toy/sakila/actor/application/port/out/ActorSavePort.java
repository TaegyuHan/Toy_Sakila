package com.toy.sakila.actor.application.port.out;

import com.toy.sakila.actor.domain.Actor;

public interface ActorSavePort {
    Actor save(Actor actor);
}
