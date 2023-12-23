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
        List<Short> ids = List.of((short) 1, (short) 2, (short) 3);

        // when
        List<Actor.ActorId> actorIds = mapper.mapToDomainEntityIds(ids);

        // then
        assertArrayEquals(
                List.of(
                        Actor.ActorId.of((short)1),
                        Actor.ActorId.of((short)2),
                        Actor.ActorId.of((short)3)
                ).toArray(),
                actorIds.toArray()
        );
    }

    @Test
    void mapToJpaEntityIds() {
        // given
        List<Actor.ActorId> actorIds = List.of(
                Actor.ActorId.of((short)1),
                Actor.ActorId.of((short)2),
                Actor.ActorId.of((short)3)
        );

        // when
        List<Short> ids = mapper.mapToJpaEntityIds(actorIds);

        // then
        assertArrayEquals(
                List.of(1, 2, 3).toArray(),
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