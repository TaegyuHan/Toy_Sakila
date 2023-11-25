package com.toy.sakila.actor.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.actor.application.port.in.ActorCreationCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
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

import static com.toy.sakila.utils.JsonComparator.assertJsonEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    void actorCreationTest() throws Exception {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        given(actorCreationUseCase.create(any(ActorCreationCommand.class)))
                .willReturn(new Actor.ActorId(1L));

        // when
        mockMvc.perform(post("/film/actor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                // then
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJsonString = """
                                {
                                  "data": {
                                    "id": 1
                                  },
                                  "message": "Actor 생성을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    assertJsonEquals(actualJson, expectedJsonString);
                });

        // then
        verify(actorCreationUseCase, times(1))
                .create(commandCaptor.capture());

        ActorCreationCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getFirstName(), commandArgument.getFirstName());
        assertEquals(command.getLastName(), commandArgument.getLastName());
    }
}