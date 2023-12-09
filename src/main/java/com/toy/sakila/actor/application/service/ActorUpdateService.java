package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorUpdateCommand;
import com.toy.sakila.actor.application.port.in.ActorUpdateUseCase;
import com.toy.sakila.actor.application.port.out.ActorReadPort;
import com.toy.sakila.actor.application.port.out.ActorSavePort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActorUpdateService implements ActorUpdateUseCase {

    private final ActorSavePort actorSavePort;
    private final ActorReadPort actorReadPort;

    @Override
    public Actor update(Actor.ActorId id, ActorUpdateCommand command) {
        Actor actor = actorReadPort.findById(id);
        actor.setFirstName(command.getFirstName());
        actor.setLastName(command.getLastName());
        return actorSavePort.save(actor);
    }
}