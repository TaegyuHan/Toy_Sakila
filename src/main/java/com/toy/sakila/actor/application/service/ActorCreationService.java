package com.toy.sakila.actor.application.service;


import com.toy.sakila.actor.application.port.in.ActorCreationCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
import com.toy.sakila.actor.application.port.out.ActorCreationPort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorCreationService implements ActorCreationUseCase {

    private final ActorCreationPort actorCreationPort;

    @Override
    public Actor.ActorId create(ActorCreationCommand command) {

        Actor actor = Actor.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();

        return actorCreationPort.create(actor);
    }
}
