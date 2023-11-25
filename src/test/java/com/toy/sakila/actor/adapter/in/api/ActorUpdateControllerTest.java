package com.toy.sakila.actor.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.actor.application.port.in.ActorUpdateCommand;
import com.toy.sakila.actor.application.port.in.ActorUpdateUseCase;
import com.toy.sakila.actor.domain.Actor;
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

import static com.toy.sakila.utils.JsonComparator.assertJsonEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    private ArgumentCaptor<ActorUpdateCommand> commandCaptor;

    @Captor
    private ArgumentCaptor<Actor.ActorId> idCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Actor | 수정")
    void actorUpdateTest() throws Exception {
        // given
        Actor.ActorId id = new Actor.ActorId(1L);

        ActorUpdateCommand command = ActorUpdateCommand.builder()
                .firstName("TaeGyuUpdate")
                .lastName("HanUpdate")
                .build();

        Actor expected = Actor.builder()
                .id(id)
                .firstName("TaeGyuUpdate")
                .lastName("HanUpdate")
                .lastUpdate(LocalDateTime.parse("2023-11-18T21:19:12"))
                .build();

        given(actorUpdateUseCase.update(any(Actor.ActorId.class), any(ActorUpdateCommand.class)))
                .willReturn(expected);

        // when
        mockMvc.perform(post("/film/actor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                                    {
                                      "data": {
                                        "id": 1,
                                        "firstName": "TaeGyuUpdate",
                                        "lastName": "HanUpdate",
                                        "lastUpdate": "2023-11-18T21:19:12"
                                      },
                                      "message": "Actor 수정을 완료했습니다.",
                                      "status": 200
                                    }
                            """;
                    assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(actorUpdateUseCase, times(1))
                .update(idCaptor.capture(), commandCaptor.capture());

        Actor.ActorId idArgument = idCaptor.getValue();
        assertEquals(id.getValue(), idArgument.getValue());

        ActorUpdateCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getFirstName(), commandArgument.getFirstName());
        assertEquals(command.getLastName(), commandArgument.getLastName());
    }
}