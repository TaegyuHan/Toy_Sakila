package com.toy.sakila.language.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.language.application.port.in.LanguageUpdateCommand;
import com.toy.sakila.language.application.port.in.LanguageUpdateUseCase;
import com.toy.sakila.language.domain.Language;
import org.json.JSONObject;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageUpdateController.class)
class LanguageUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LanguageUpdateUseCase languageUpdateUseCase;

    @Captor
    private ArgumentCaptor<LanguageUpdateCommand> commandCaptor;
    
    @Captor
    private ArgumentCaptor<Language.LanguageId> idCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Language | 수정")
    void languageUpdate() throws Exception {
        // given
        Language.LanguageId id = new Language.LanguageId(1L);
        LanguageUpdateCommand command = LanguageUpdateCommand.builder()
                .name("Animation")
                .build();

        Language expected = Language.builder()
                .id(id)
                .name(command.getName())
                .lastUpdate(LocalDateTime.parse("2023-11-18T21:19:12"))
                .build();

        given(languageUpdateUseCase.update(id, command))
                .willReturn(expected);

        // when
        mockMvc.perform(post("/film/languages/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    JSONObject actualJSON = new JSONObject(actualJson);
                    JSONObject expectedJSON = new JSONObject("""
                                {
                                  "data": {
                                    "lastUpdate": "2023-11-18T21:19:12",
                                    "name": "Animation",
                                    "id": 1
                                  },
                                  "message": "Language 수정을 완료했습니다.",
                                  "status": 200
                                }
                            """);
                    assertEquals(expectedJSON.toString(), actualJSON.toString());
                });

        // then
        verify(languageUpdateUseCase, times(1))
                .update(idCaptor.capture(), commandCaptor.capture());

        Language.LanguageId idArgument = idCaptor.getValue();
        assertEquals(id.getValue(), idArgument.getValue());

        LanguageUpdateCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getName(), commandArgument.getName());
    }
}