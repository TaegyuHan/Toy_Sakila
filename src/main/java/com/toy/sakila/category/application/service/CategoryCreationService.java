package com.toy.sakila.category.application.service;

import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.in.CategoryCreationUseCase;
import com.toy.sakila.category.application.port.out.CategorySavePort;
import com.toy.sakila.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CategoryCreationService implements CategoryCreationUseCase {

    private final CategorySavePort categorySavePort;

    @Override
    public Category create(CategoryCreationCommand command) {
        Category category = Category.builder()
                .name(command.getName())
                .build();
        return categorySavePort.save(category);
    }
}