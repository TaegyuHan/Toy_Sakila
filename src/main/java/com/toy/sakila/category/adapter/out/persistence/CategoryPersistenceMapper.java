package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.Mapper;

import java.util.List;

@Mapper
public class CategoryPersistenceMapper {

    public CategoryJpaEntity mapToJpaEntity(Category domain) {
        return CategoryJpaEntity.builder()
                .categoryId(domain.getId().getValue())
                .name(domain.getName())
                .build();
    }

    public Category mapToDomainEntity(CategoryJpaEntity entity) {
        return Category.builder()
                .id(Category.CategoryId.of(entity.getCategoryId()))
                .name(entity.getName())
                .lastUpdate(entity.getLastUpdate())
                .build();
    }

    public List<Byte> mapToJpaEntityIds(List<Category.CategoryId> domainIds) {
        return domainIds.stream()
                .map(Category.CategoryId::getValue)
                .toList();
    }

    public List<Category> mapToDomainEntities(List<CategoryJpaEntity> categoryJpaEntities) {
        return categoryJpaEntities.stream()
                .map(this::mapToDomainEntity)
                .toList();
    }
}