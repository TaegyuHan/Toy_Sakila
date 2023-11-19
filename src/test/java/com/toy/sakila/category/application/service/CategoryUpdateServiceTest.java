package com.toy.sakila.category.application.service;


import com.toy.sakila.category.application.port.in.CategoryUpdateCommand;
import com.toy.sakila.category.application.port.out.CategoryUpdatePort;
import com.toy.sakila.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class CategoryUpdateServiceTest {

    @InjectMocks
    private CategoryUpdateService categoryUpdateService;

    @Mock
    private CategoryUpdatePort categoryUpdatePort;

    @Test
    @DisplayName("성공 | Service | Category | 수정")
    void update() {
        // given
        CategoryUpdateCommand command = CategoryUpdateCommand.builder()
                .name("Animation")
                .build();
        Category.CategoryId id = new Category.CategoryId(1L);

        Category expected = Category.builder()
                .id(id)
                .name("Animation")
                .build();

        given(categoryUpdatePort.update(any(Category.class)))
                .willReturn(expected);

        // when
        Category result = categoryUpdateService.update(id, command);

        // then
        verify(categoryUpdatePort, times(1)).update(any(Category.class));
        assertEquals(expected, result);
    }
}