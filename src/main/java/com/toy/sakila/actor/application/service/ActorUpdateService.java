package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorUpdateCommand;
import com.toy.sakila.actor.application.port.in.ActorUpdateUseCase;
import com.toy.sakila.actor.application.port.out.ActorUpdatePort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActorUpdateService implements ActorUpdateUseCase {

    private final ActorUpdatePort actorUpdatePort;

    @Override
    public Actor update(Actor.ActorId id, ActorUpdateCommand command) {
        Actor actor = Actor.builder()
                .id(id)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();
        return actorUpdatePort.update(actor);
    }
}