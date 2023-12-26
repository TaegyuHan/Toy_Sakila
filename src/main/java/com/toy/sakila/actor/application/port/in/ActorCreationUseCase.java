package com.toy.sakila.actor.application.port.in;

import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.UseCase;


@UseCase
public interface ActorCreationUseCase {
    Actor create(ActorCommand command);
}