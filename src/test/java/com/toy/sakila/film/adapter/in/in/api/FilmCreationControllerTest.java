package com.toy.sakila.film.adapter.in.in.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.film.application.port.in.FilmCreationCommand;
import com.toy.sakila.film.application.port.in.FilmCreationUseCase;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.utils.JsonComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmCreationController.class)
class FilmCreationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilmCreationUseCase categoryCreationUseCase;

    @Captor
    private ArgumentCaptor<FilmCreationCommand> commandCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Film | 생성")
    void categoryCreation() throws Exception {
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

        given(categoryCreationUseCase.create(command))
                .willReturn(Film.FilmId.of(1L));

        // when
        mockMvc.perform(post("/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                                {
                                  "data": {
                                    "id": 1
                                  },
                                  "message": "Film 생성을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    JsonComparator.assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(categoryCreationUseCase, times(1))
                .create(commandCaptor.capture());

    }
}