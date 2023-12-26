package com.toy.sakila.actor.adapter.in.api;


import com.toy.sakila.actor.application.port.in.ActorReadUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.utils.JsonComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.toy.sakila.config.Version.API_PREFIX;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ActorReadController.class)
class ActorReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorReadUseCase actorReadUseCase;

    @Test
    @DisplayName("REST API | GET | Actor | 목록 조회")
    void actorList() throws Exception {
        // given
        List<Actor> actors = List.of(
                Actor.builder()
                        .id(Actor.ActorId.of((short) 1))
                        .firstName("NICK")
                        .lastName("WAHLBERG")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .createDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build(),
                Actor.builder()
                        .id(Actor.ActorId.of((short) 2))
                        .firstName("ED")
                        .lastName("CHASE")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .createDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build(),
                Actor.builder()
                        .id(Actor.ActorId.of((short) 3))
                        .firstName("JENNIFER")
                        .lastName("DAVIS")
                        .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .createDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                        .build()
        );
        given(actorReadUseCase.findAll())
                .willReturn(actors);

        // when
        mockMvc.perform(get(API_PREFIX + "/film/actor"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                            {
                              "data": {
                                "actors": [
                                  {
                                    "id": { "value": 1 },
                                    "firstName": "NICK",
                                    "lastName": "WAHLBERG",
                                    "lastUpdate": "2023-01-01T01:01:01",
                                    "createDate": "2023-01-01T01:01:01"
                                  },
                                  {
                                    "id": { "value": 2 },
                                    "firstName": "ED",
                                    "lastName": "CHASE",
                                    "lastUpdate": "2023-01-01T01:01:01",
                                    "createDate": "2023-01-01T01:01:01"
                                  },
                                  {
                                    "id": { "value": 3 },
                                    "firstName": "JENNIFER",
                                    "lastName": "DAVIS",
                                    "lastUpdate": "2023-01-01T01:01:01",
                                    "createDate": "2023-01-01T01:01:01"
                                  }
                                ]
                              },
                              "message": "Actor 목록 조회를 완료했습니다.",
                              "status": 200
                            }
                            """;
                    JsonComparator.assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(actorReadUseCase, times(1))
                .findAll();
    }
}