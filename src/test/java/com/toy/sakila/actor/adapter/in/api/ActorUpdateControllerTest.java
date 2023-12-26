package com.toy.sakila.actor.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.actor.application.port.in.ActorCommand;
import com.toy.sakila.actor.application.port.in.ActorUpdateUseCase;
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

import static com.toy.sakila.config.Version.API_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActorUpdateController.class)
class ActorUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorUpdateUseCase actorUpdateUseCase;

    @Captor
    private ArgumentCaptor<ActorCommand> commandCaptor;

    @Test
    @DisplayName("REST API | POST | Actor | 수정")
    void actorUpdate() throws Exception {
        // given
        short actorId = 1;

        ActorCommand command = ActorCommand.builder()
                .firstName("NICK")
                .lastName("UPDATED")
                .build();

        Actor domain = Actor.builder()
                .id(Actor.ActorId.of((short) 1L))
                .firstName("NICK")
                .lastName("UPDATED")
                .lastUpdate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .createDate(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .build();

        given(actorUpdateUseCase.update(Actor.ActorId.of((short) 1L), command))
                .willReturn(domain);

        // when
        mockMvc.perform(post(API_PREFIX + "/film/actor/" + actorId)
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
                                    "lastName": "UPDATED",
                                    "lastUpdate": "2023-01-01T01:01:01",
                                    "createDate": "2023-01-01T01:01:01"
                                  },
                                  "message": "Actor 수정을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    JsonComparator.assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(actorUpdateUseCase, times(1))
                .update(eq(Actor.ActorId.of((short) 1L)), commandCaptor.capture());
        assertThat(commandCaptor.getValue()).isEqualTo(command);
    }
}