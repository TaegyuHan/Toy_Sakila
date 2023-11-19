package com.toy.sakila.language.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationUseCase;
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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageCreationController.class)
class LanguageCreationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LanguageCreationUseCase categoryCreationUseCase;

    @Captor
    private ArgumentCaptor<LanguageCreationCommand> commandCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Language | 생성")
    void categoryCreation() throws Exception {
        // given
        LanguageCreationCommand command = LanguageCreationCommand.builder()
                .name("English")
                .build();

        given(categoryCreationUseCase.create(command))
                .willReturn(new Language.LanguageId(1L));

        // when
        mockMvc.perform(post("/film/language")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    JSONObject actualJSON = new JSONObject(actualJson);
                    JSONObject expectedJSON = new JSONObject("""
                                {
                                  "data": {
                                    "id": 1
                                  },
                                  "message": "Language 생성을 완료했습니다.",
                                  "status": 200
                                }
                            """);
                    assertEquals(expectedJSON.toString(), actualJSON.toString());
                });

        // then
        verify(categoryCreationUseCase, times(1))
                .create(commandCaptor.capture());

        LanguageCreationCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getName(), commandArgument.getName());
    }
}