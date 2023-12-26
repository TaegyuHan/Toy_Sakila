package com.toy.sakila.actor.application.port.in;

import com.toy.sakila.actor.domain.Actor;

import java.util.List;

public interface ActorReadUseCase {
    List<Actor> findAll();
}
