package com.toy.sakila.film.application.service;


import com.toy.sakila.actor.application.port.out.ActorReadPort;
import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.film.application.port.in.FilmCreationCommand;
import com.toy.sakila.film.application.port.out.FilmCreationPort;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.language.application.port.out.LanguageReadPort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class FilmCreationServiceTest {

    @InjectMocks
    private FilmCreationService categoryCreationService;

    @Mock
    private FilmCreationPort categoryCreationPort;

    @Mock
    private ActorReadPort actorReadPort;

    @Mock
    private CategoryReadPort categoryReadPort;

    @Mock
    private LanguageReadPort languageReadPort;

    @Test
    @DisplayName("성공 | Service | Film | 생성")
    void create() {
        // given
        FilmCreationCommand command = FilmCreationCommand.builder()
                .title("ACE GOLDFINGER")
                .description("A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China")
                .releaseYear((short) 2006)
                // .actorIds(...) // actorIds 목록이 제공되지 않았으므로 생략
                // .categoryIds(...) // categoryIds 목록이 제공되지 않았으므로 생략
                .languageId(1L)
                .originalLanguageId(null) // NULL 값은 null로 표현
                .rentalDuration((short) 3)
                .rentalRate(BigDecimal.valueOf(4.99))
                .length((short) 48)
                .replacementCost(BigDecimal.valueOf(12.99))
                // .rating(...) // EnumFilmRating 값이 제공되지 않았으므로 생략
                // .specialFeatures(...) // EnumSpecialFeature 목록이 제공되지 않았으므로 생략
                .build();

        Film.FilmId expected = Film.FilmId.of(1L);

        given(categoryCreationPort.create(any(Film.class)))
                .willReturn(expected);

        given(actorReadPort.findByIdIn(command.getActorIds()))
                .willReturn(null);

        given(categoryReadPort.findByIdIn(command.getCategoryIds()))
                .willReturn(null);

        given(languageReadPort.findById(new Language.LanguageId(command.getLanguageId())))
                .willReturn(null);

        // when
        Film.FilmId result = categoryCreationService.create(command);

        // then
        assertEquals(expected, result);
        verify(categoryCreationPort).create(any(Film.class));
    }
}