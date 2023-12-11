package com.toy.sakila.category.application.port.in;

import com.toy.sakila.category.domain.Category;


public interface CategoryUpdateUseCase {
    Category update(Category.CategoryId id, CategoryUpdateCommand command);
}