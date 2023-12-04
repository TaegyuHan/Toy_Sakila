package com.toy.sakila.actor.application.port.out;

import com.toy.sakila.actor.domain.Actor;

import java.util.List;

public interface ActorReadPort {
    List<Actor> findByIdIn(List<Long> ids);
}