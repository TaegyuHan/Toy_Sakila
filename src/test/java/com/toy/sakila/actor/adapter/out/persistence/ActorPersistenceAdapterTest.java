package com.toy.sakila.actor.adapter.out.persistence;


import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ActorPersistenceAdapterTest {

    @Mock
    private SpringDataActorRepository springDataRepository;
    @Mock
    private ActorPersistenceMapper mapper;

    @InjectMocks
    private ActorPersistenceAdapter actorPersistenceAdapter;

    @Test
    @DisplayName("성공 | PersistenceAdapter | Actor | 생성")
    void create() {
        // given
        Actor actor = Actor.builder()
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        ActorJpaEntity expected = ActorJpaEntity.builder()
                .id(1L)
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        given(mapper.mapToJpaEntity(any(Actor.class)))
                .willReturn(expected);
        given(springDataRepository.save(any(ActorJpaEntity.class)))
                .willReturn(expected);

        // when
        Actor.ActorId result = actorPersistenceAdapter.create(actor);

        // then
        assertEquals(expected.getId(), result.getValue());
    }

    @Test
    @DisplayName("성공 | PersistenceAdapter | Actor | 수정")
    void update() {
        // given
        Actor actor = Actor.builder()
                .id(new Actor.ActorId(1L))
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        ActorJpaEntity entity = ActorJpaEntity.builder()
                .id(1L)
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        Actor updatedActor = Actor.builder()
                .id(new Actor.ActorId(1L))
                .firstName("TaeGyuUpdate")
                .lastName("HanUpdate")
                .build();

        given(springDataRepository.findById(anyLong()))
                .willReturn(Optional.of(entity));
        given(springDataRepository.save(any(ActorJpaEntity.class)))
                .willReturn(entity);
        given(mapper.mapToDomainEntity(any(ActorJpaEntity.class)))
                .willReturn(updatedActor);

        // when
        Actor result = actorPersistenceAdapter.update(actor);

        // then
        assertEquals(updatedActor, result);
        verify(springDataRepository).save(entity);
    }
}