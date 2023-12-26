package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.application.port.out.ActorDeletePort;
import com.toy.sakila.actor.application.port.out.ActorReadPort;
import com.toy.sakila.actor.application.port.out.ActorSavePort;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@PersistenceAdapter
@Transactional
public class ActorPersistenceAdapter
        implements ActorReadPort, ActorSavePort, ActorDeletePort {

    private final SpringDataActorRepository springDataActorRepository;
    private final ActorPersistenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Actor> findByIdIn(List<Actor.ActorId> ids) {
        return Optional.of(ids)
                        .map(mapper::mapToJpaEntityIds)
                        .map(springDataActorRepository::findByActorIdIn)
                        .map(mapper::mapToDomainEntities)
                        .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Actor findById(Actor.ActorId id) {
        return springDataActorRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Actor> findAll() {
        return springDataActorRepository.findAll()
                .stream()
                .map(mapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Actor save(Actor actor) {
        return Optional.of(actor)
                .map(mapper::mapToJpaEntity)
                .map(springDataActorRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public void delete(Actor.ActorId id) {
        springDataActorRepository.deleteById(id.getValue());
    }
}