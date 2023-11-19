package com.toy.sakila.category.adapter.in.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.in.CategoryCreationUseCase;
import com.toy.sakila.category.domain.Category;
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

@WebMvcTest(CategoryCreationController.class)
class CategoryCreationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryCreationUseCase categoryCreationUseCase;

    @Test
    @DisplayName("성공 | REST API | POST | Category | 생성")
    void categoryCreation() throws Exception {
        // given
        CategoryCreationCommand command = CategoryCreationCommand.builder()
                .name("Animation")
                .build();

        given(categoryCreationUseCase.create(command))
                .willReturn(new Category.CategoryId(1L));

        // when
        mockMvc.perform(post("/film/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }
}