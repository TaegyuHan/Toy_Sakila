package com.toy.sakila.category.application.port.out;

import com.toy.sakila.category.domain.Category;
import static com.toy.sakila.category.domain.Category.*;

public interface CategoryCreationPort {
    CategoryId create(Category category);

}