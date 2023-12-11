package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


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
    @DisplayName("성공 | PersistenceAdapter | Actor | findByIdIn")
    void findByIdIn() {
        // given
        List<Actor.ActorId> ids = List.of(
                Actor.ActorId.of(1L),
                Actor.ActorId.of(2L),
                Actor.ActorId.of(3L)
        );

        List<Long> jpaEntityIds = List.of(1L, 2L, 3L);

        List<ActorJpaEntity> jpaEntities = List.of(
                ActorJpaEntity.builder()
                        .id(1L)
                        .firstName("Test Actor 1")
                        .lastName("Test Actor 1")
                        .build(),
                ActorJpaEntity.builder()
                        .id(2L)
                        .firstName("Test Actor 2")
                        .lastName("Test Actor 2")
                        .build(),
                ActorJpaEntity.builder()
                        .id(3L)
                        .firstName("Test Actor 3")
                        .lastName("Test Actor 3")
                        .build()
        );

        List<Actor> expected = List.of(
                Actor.builder()
                        .id(Actor.ActorId.of(1L))
                        .firstName("Test Actor 1")
                        .lastName("Test Actor 1")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build(),
                Actor.builder()
                        .id(Actor.ActorId.of(2L))
                        .firstName("Test Actor 2")
                        .lastName("Test Actor 2")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build(),
                Actor.builder()
                        .id(Actor.ActorId.of(3L))
                        .firstName("Test Actor 3")
                        .lastName("Test Actor 3")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build()
        );

        given(mapper.mapToJpaEntityIds(ids))
                .willReturn(jpaEntityIds);
        given(springDataRepository.findByIdIn(jpaEntityIds))
                .willReturn(jpaEntities);
        given(mapper.mapToDomainEntities(jpaEntities))
                .willReturn(expected);

        // when
        List<Actor> result = actorPersistenceAdapter.findByIdIn(ids);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("성공 | PersistenceAdapter | Actor | findById")
    void findById() {
        // given
        Actor.ActorId id = Actor.ActorId.of(1L);

        given(springDataRepository.findById(anyLong()))
                .willReturn(Optional.of(
                        ActorJpaEntity.builder()
                                .id(1L)
                                .firstName("Test Actor")
                                .lastName("Test Actor")
                                .build()
                ));

        given(mapper.mapToDomainEntity(any(ActorJpaEntity.class)))
                .willReturn(Actor.builder()
                        .id(Actor.ActorId.of(1L))
                        .firstName("Test Actor")
                        .lastName("Test Actor")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .createdDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build());

        // when
        Actor result = actorPersistenceAdapter.findById(id);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getFirstName()).isEqualTo("Test Actor");
        assertThat(result.getLastName()).isEqualTo("Test Actor");
        assertThat(result.getLastUpdate()).isEqualTo(LocalDateTime.of(2023, 1, 1, 1, 1, 1));
        assertThat(result.getCreatedDate()).isEqualTo(LocalDateTime.of(2023, 1, 1, 1, 1, 1));
    }

    @Test
    @DisplayName("성공 | PersistenceAdapter | Actor | save")
    void save() {
        // given
        Actor actor = Actor.builder()
                .id(Actor.ActorId.of(1L))
                .firstName("Test Actor")
                .lastName("Test Actor")
                .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .createdDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .build();

        given(mapper.mapToJpaEntity(any(Actor.class)))
                .willReturn(ActorJpaEntity.builder()
                        .id(1L)
                        .firstName("Test Actor")
                        .lastName("Test Actor")
                        .build());

        given(springDataRepository.save(any(ActorJpaEntity.class))).willReturn(
                ActorJpaEntity.builder()
                        .id(1L)
                        .firstName("Test Actor")
                        .lastName("Test Actor")
                        .build()
        );

        given(mapper.mapToDomainEntity(any(ActorJpaEntity.class)))
                .willReturn(Actor.builder()
                        .id(Actor.ActorId.of(1L))
                        .firstName("Test Actor")
                        .lastName("Test Actor")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .createdDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build());

        // when
        Actor result = actorPersistenceAdapter.save(actor);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(Actor.ActorId.of(1L));
        assertThat(result.getFirstName()).isEqualTo("Test Actor");
        assertThat(result.getLastName()).isEqualTo("Test Actor");
        assertThat(result.getLastUpdate()).isEqualTo(LocalDateTime.of(2023, 1, 1, 1, 1, 1));
        assertThat(result.getCreatedDate()).isEqualTo(LocalDateTime.of(2023, 1, 1, 1, 1, 1));
    }
}