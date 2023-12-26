package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorDeleteUseCase;
import com.toy.sakila.actor.application.port.out.ActorDeletePort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActorDeleteService implements ActorDeleteUseCase {

    private final ActorDeletePort actorDeletePort;

    @Override
    public void delete(Actor.ActorId id) {
        actorDeletePort.delete(id);
    }
}
