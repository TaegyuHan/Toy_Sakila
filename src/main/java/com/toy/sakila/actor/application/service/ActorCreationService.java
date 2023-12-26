package com.toy.sakila.actor.application.service;


import com.toy.sakila.actor.application.port.in.ActorCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
import com.toy.sakila.actor.application.port.out.ActorSavePort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ActorCreationService implements ActorCreationUseCase {

    private final ActorSavePort actorSavePort;

    @Override
    public Actor create(ActorCommand command) {
        Actor actor = command.toDomainEntity();
        return actorSavePort.save(actor);
    }
}
