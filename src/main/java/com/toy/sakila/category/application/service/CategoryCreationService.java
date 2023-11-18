package com.toy.sakila.category.application.service;

import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.in.CategoryCreationUseCase;
import com.toy.sakila.category.application.port.out.CategoryCreationPort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.category.domain.Category.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreationService implements CategoryCreationUseCase {

    private final CategoryCreationPort categoryCreationPort;

    @Override
    public CategoryId create(CategoryCreationCommand command) {
        Category category = Category.builder()
                .name(command.getName())
                .build();
        return categoryCreationPort.create(category);
    }
}