package com.toy.sakila.film.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class FilmPersistenceAdapterTest {

    @Mock
    private SpringDataFilmRepository springDataRepository;
    @Mock
    private FilmPersistenceMapper mapper;

    @InjectMocks
    private FilmPersistenceAdapter actorPersistenceAdapter;

    @Test
    @DisplayName("성공 | PersistenceAdapter | Film | 생성")
    void create() {
        // given
        Film actor = Film.builder()
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        // 여기 부분 진행해야함

        FilmJpaEntity expected = FilmJpaEntity.builder()
                .id(1L)
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        given(mapper.mapToJpaEntity(any(Film.class)))
                .willReturn(expected);
        given(springDataRepository.save(any(FilmJpaEntity.class)))
                .willReturn(expected);

        // when
        Film.FilmId result = actorPersistenceAdapter.create(actor);

        // then
        assertEquals(expected.getId(), result.getValue());
    }
}