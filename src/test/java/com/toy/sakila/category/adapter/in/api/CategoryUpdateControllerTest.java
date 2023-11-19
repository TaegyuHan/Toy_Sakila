package com.toy.sakila.category.adapter.in.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.sakila.category.application.port.in.CategoryUpdateCommand;
import com.toy.sakila.category.application.port.in.CategoryUpdateUseCase;
import com.toy.sakila.category.domain.Category;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryUpdateController.class)
class CategoryUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryUpdateUseCase categoryUpdateUseCase;

    @Captor
    private ArgumentCaptor<CategoryUpdateCommand> commandCaptor;

    @Captor
    private ArgumentCaptor<Category.CategoryId> idCaptor;

    @Test
    @DisplayName("성공 | REST API | POST | Category | 수정")
    void categoryUpdateTest() throws Exception {
        // given
        Category.CategoryId id = new Category.CategoryId(1L);

        CategoryUpdateCommand command = CategoryUpdateCommand.builder()
                .name("Animation")
                .build();

        Category expected = Category.builder()
                .id(id)
                .name(command.getName())
                .lastUpdate(LocalDateTime.parse("2023-11-18T21:19:12"))
                .build();

        given(categoryUpdateUseCase.update(any(Category.CategoryId.class), any(CategoryUpdateCommand.class)))
                .willReturn(expected);

        // when
        mockMvc.perform(post("/film/category/1")
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
                                  "message": "Category 수정을 완료했습니다.",
                                  "status": 200
                                }
                        """);
                    assertEquals(expectedJSON.toString(), actualJSON.toString());
                });

        // then
        verify(categoryUpdateUseCase, times(1))
                .update(idCaptor.capture(), commandCaptor.capture());

        Category.CategoryId idArgument = idCaptor.getValue();
        assertEquals(id.getValue(), idArgument.getValue());

        CategoryUpdateCommand commandArgument = commandCaptor.getValue();
        assertEquals(command.getName(), commandArgument.getName());
    }
}