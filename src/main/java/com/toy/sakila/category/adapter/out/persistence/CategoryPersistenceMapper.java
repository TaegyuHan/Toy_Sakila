package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.Mapper;

@Mapper
public class CategoryPersistenceMapper {

        public CategoryJpaEntity mapToJpaEntity(Category category) {
            return CategoryJpaEntity.builder()
                    .id(category.getId().getValue())
                    .name(category.getName())
                    .build();
        }
}