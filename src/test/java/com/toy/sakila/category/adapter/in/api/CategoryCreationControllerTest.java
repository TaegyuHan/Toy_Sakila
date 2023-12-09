package com.toy.sakila.category.adapter.in.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.in.CategoryCreationUseCase;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.utils.JsonComparator;
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

import static com.toy.sakila.utils.JsonComparator.assertJsonEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Captor
    private ArgumentCaptor<CategoryCreationCommand> commandCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Category | 생성")
    void categoryCreationTest() throws Exception {
        // given
        CategoryCreationCommand command = CategoryCreationCommand.builder()
                .name("Animation")
                .build();

        given(categoryCreationUseCase.create(any(CategoryCreationCommand.class)))
                .willReturn(Category.CategoryId.of(1L));

        // when
        mockMvc.perform(post("/film/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                // then
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String actualJson = result.getResponse().getContentAsString();
                    String expectedJson = """
                                {
                                  "data": {
                                    "id": 1
                                  },
                                  "message": "Category 생성을 완료했습니다.",
                                  "status": 200
                                }
                            """;
                    assertJsonEquals(actualJson, expectedJson);
                });

        // then
        verify(categoryCreationUseCase, times(1))
                .create(commandCaptor.capture());

        CategoryCreationCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getName(), commandArgument.getName());
    }
}