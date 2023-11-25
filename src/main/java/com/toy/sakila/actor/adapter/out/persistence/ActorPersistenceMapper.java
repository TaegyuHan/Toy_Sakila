package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.Mapper;

@Mapper
public class ActorPersistenceMapper {
    public ActorJpaEntity mapToJpaEntity(Actor actor) {
        return ActorJpaEntity.builder()
                .id(actor.getId().getValue())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build();
    }

    public Actor mapToDomainEntity(ActorJpaEntity entity) {
        return Actor.builder()
                .id(new Actor.ActorId(entity.getId()))
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .lastUpdate(entity.getLastUpdate())
                .build();
    }
}