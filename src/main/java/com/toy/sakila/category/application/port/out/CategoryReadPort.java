package com.toy.sakila.category.application.port.out;

import com.toy.sakila.category.domain.Category;

import java.util.List;

public interface CategoryReadPort {
    List<Category> findByIdIn(List<Category.CategoryId> ids);
    Category findById(Category.CategoryId id);
}