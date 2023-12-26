package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorReadUseCase;
import com.toy.sakila.actor.application.port.out.ActorReadPort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorReadService implements ActorReadUseCase {

    private final ActorReadPort actorReadPort;

    @Override
    public List<Actor> findAll() {
        return actorReadPort.findAll();
    }
}
