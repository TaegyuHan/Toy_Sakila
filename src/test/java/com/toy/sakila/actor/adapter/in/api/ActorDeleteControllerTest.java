package com.toy.sakila.actor.adapter.in.api;


import com.toy.sakila.actor.application.port.in.ActorDeleteUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.utils.JsonComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.toy.sakila.config.Version.API_PREFIX;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActorDeleteController.class)
class ActorDeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorDeleteUseCase actorDeleteUseCase;

    @Test
    @DisplayName("REST API | DELETE | Actor | 삭제")
    void actorDelete() throws Exception {
        // given
        short actorId = 1;

        // when
        mockMvc.perform(delete(API_PREFIX + "/film/actor/" + actorId))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                                {
                                  "data": {
                                    "id": 1
                                  },
                                  "message": "Actor 삭제을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    JsonComparator.assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(actorDeleteUseCase, times(1))
                .delete(eq(Actor.ActorId.of((short) 1L)));
    }


}