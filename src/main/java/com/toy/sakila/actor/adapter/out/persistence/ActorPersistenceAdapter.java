package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.application.port.out.ActorCreationPort;
import com.toy.sakila.actor.application.port.out.ActorUpdatePort;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ActorPersistenceAdapter
        implements ActorCreationPort, ActorUpdatePort {

    private final ActorPersistenceMapper mapper;
    private final SpringDataActorRepository springDataActorRepository;

    @Override
    public Actor.ActorId create(Actor actor) {
        ActorJpaEntity entity = springDataActorRepository.save(mapper.mapToJpaEntity(actor));
        return new Actor.ActorId(entity.getId());
    }

    @Override
    public Actor update(Actor actor) {
        ActorJpaEntity entity = springDataActorRepository.findById(actor.getId().getValue())
                .orElseThrow();
        entity.setFirstName(actor.getFirstName());
        entity.setLastName(actor.getLastName());
        return mapper.mapToDomainEntity(springDataActorRepository.save(entity));
    }
}