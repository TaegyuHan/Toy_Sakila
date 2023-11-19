package com.toy.sakila.language.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationUseCase;
import com.toy.sakila.language.domain.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
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
                .andExpect(status().isOk());
    }
}