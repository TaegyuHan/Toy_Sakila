package com.toy.sakila.actor.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.actor.application.port.in.ActorCreationCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
import com.toy.sakila.actor.domain.Actor;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ActorCreationController.class)
class ActorCreationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorCreationUseCase actorCreationUseCase;

    @Captor
    private ArgumentCaptor<ActorCreationCommand> commandCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Actor | 생성")
    void actorCreation() throws Exception {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .firstName("NICK")
                .lastName("WAHLBERG")
                .build();

        Actor domain = Actor.builder()
                .id(Actor.ActorId.of(1L))
                .firstName("NICK")
                .lastName("WAHLBERG")
                .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .createdDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .build();

        given(actorCreationUseCase.create(command))
                .willReturn(domain);

        // when
        mockMvc.perform(post("/film/actor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                                {
                                  "data": {
                                    "id": 1,
                                    "firstName": "NICK",
                                    "lastName": "WAHLBERG",
                                    "lastUpdate": "2023-01-01T01:01:01",
                                    "createdDate": "2023-01-01T01:01:01"
                                  },
                                  "message": "Actor 생성을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    JsonComparator.assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(actorCreationUseCase, times(1))
                .create(commandCaptor.capture());
        assertThat(commandCaptor.getValue()).isEqualTo(command);
    }
}