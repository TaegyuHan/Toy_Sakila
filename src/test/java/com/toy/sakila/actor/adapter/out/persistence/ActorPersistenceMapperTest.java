package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.domain.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActorPersistenceMapperTest {

    private ActorPersistenceMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ActorPersistenceMapper();
    }

    @Test
    void mapToDomainEntityIds() {
        // given
        List<Long> ids = List.of(1L, 2L, 3L);

        // when
        List<Actor.ActorId> actorIds = mapper.mapToDomainEntityIds(ids);

        // then
        assertArrayEquals(
                List.of(
                        Actor.ActorId.of(1L),
                        Actor.ActorId.of(2L),
                        Actor.ActorId.of(3L)
                ).toArray(),
                actorIds.toArray()
        );
    }

    @Test
    void mapToJpaEntityIds() {
        // given
        List<Actor.ActorId> actorIds = List.of(
                Actor.ActorId.of(1L),
                Actor.ActorId.of(2L),
                Actor.ActorId.of(3L)
        );

        // when
        List<Long> ids = mapper.mapToJpaEntityIds(actorIds);

        // then
        assertArrayEquals(
                List.of(1L, 2L, 3L).toArray(),
                ids.toArray()
        );
    }

    @Test
    void mapToJpaEntities() {

    }

    @Test
    void mapToDomainEntities() {
        // given
        // when
        // then
    }

    @Test
    void mapToJpaEntity() {
        // given
        // when
        // then
    }

    @Test
    void mapToDomainEntity() {
        // given
        // when
        // then
    }
}