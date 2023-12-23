package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.Mapper;

import java.util.List;


@Mapper
public class ActorPersistenceMapper {

    public List<Actor.ActorId> mapToDomainEntityIds(List<Short> ids) {
        return ids.stream()
                .map(Actor.ActorId::of)
                .toList();
    }

    public List<Short> mapToJpaEntityIds(List<Actor.ActorId> ids) {
        return ids.stream()
                .map(Actor.ActorId::getValue)
                .toList();
    }

    public List<ActorJpaEntity> mapToJpaEntities(List<Actor> actors) {
        return actors.stream()
                .map(this::mapToJpaEntity)
                .toList();
    }

    public List<Actor> mapToDomainEntities(List<ActorJpaEntity> entities) {
        return entities.stream()
                .map(this::mapToDomainEntity)
                .toList();
    }

    public ActorJpaEntity mapToJpaEntity(Actor actor) {
        return ActorJpaEntity.builder()
                .actorId(actor.getId().getValue())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build();
    }

    public Actor mapToDomainEntity(ActorJpaEntity entity) {
        return Actor.builder()
                .id(Actor.ActorId.of(entity.getActorId()))
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .lastUpdate(entity.getLastUpdate())
                .createDate(entity.getCreateDate())
                .build();
    }
}