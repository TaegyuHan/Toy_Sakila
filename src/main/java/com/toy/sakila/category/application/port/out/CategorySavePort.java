package com.toy.sakila.category.application.port.out;

import com.toy.sakila.category.domain.Category;


public interface CategorySavePort {
    Category save(Category category);
}
