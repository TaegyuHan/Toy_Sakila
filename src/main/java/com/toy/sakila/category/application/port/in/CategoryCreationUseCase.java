package com.toy.sakila.category.application.port.in;

import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.UseCase;

@UseCase
public interface CategoryCreationUseCase {
    Category create(CategoryCreationCommand command);
}