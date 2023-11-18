package com.toy.sakila.category.application.service;

import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.out.CategoryCreationPort;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class CategoryCreationServiceTest {

    @InjectMocks
    private CategoryCreationService categoryCreationService;

    @Mock
    private CategoryCreationPort categoryCreationPort;

    @Test
    @DisplayName("성공 | Service | Category | 생성")
    void create() {
        // given
        CategoryCreationCommand command = CategoryCreationCommand.builder()
                .name("Animation")
                .build();
        Category.CategoryId expected = new Category.CategoryId(1L);

        // when
        when(categoryCreationService.create(command))
                .thenReturn(expected);
        Category.CategoryId result = categoryCreationService.create(command);

        // that
        assertEquals(expected, result);
        verify(categoryCreationPort).create(any(Category.class));
    }
}