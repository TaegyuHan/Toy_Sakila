package com.toy.sakila.category.application.service;

import com.toy.sakila.category.application.port.in.CategoryUpdateCommand;
import com.toy.sakila.category.application.port.in.CategoryUpdateUseCase;
import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.category.application.port.out.CategorySavePort;
import com.toy.sakila.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateService implements CategoryUpdateUseCase {

    private final CategorySavePort categorySavePort;
    private final CategoryReadPort categoryReadPort;

    @Override
    public Category update(Category.CategoryId id, CategoryUpdateCommand command) {
        Category domain = categoryReadPort.findById(id);
        domain.setName(command.getName());
        return categorySavePort.save(domain);
    }
}