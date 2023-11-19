package com.toy.sakila.category.application.service;

import com.toy.sakila.category.application.port.in.CategoryUpdateCommand;
import com.toy.sakila.category.application.port.in.CategoryUpdateUseCase;
import com.toy.sakila.category.application.port.out.CategoryUpdatePort;
import com.toy.sakila.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateService implements CategoryUpdateUseCase {

    private final CategoryUpdatePort categoryUpdatePort;

    @Override
    public Category update(Category.CategoryId id, CategoryUpdateCommand command) {
        Category category = Category.builder()
                .id(id)
                .name(command.getName())
                .build();
        return categoryUpdatePort.update(category);
    }
}