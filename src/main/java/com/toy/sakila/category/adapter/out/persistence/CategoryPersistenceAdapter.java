package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.category.application.port.out.CategoryCreationPort;
import com.toy.sakila.category.application.port.out.CategoryUpdatePort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.PersistenceAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class CategoryPersistenceAdapter
        implements CategoryCreationPort, CategoryUpdatePort {

    private final SpringDataCategoryRepository springDataRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    @Transactional
    public Category.CategoryId create(Category category) {
        CategoryJpaEntity entity = springDataRepository.save(mapper.mapToJpaEntity(category));
        return new Category.CategoryId(entity.getId());
    }

    @Override
    public Category update(Category category) {
        CategoryJpaEntity entity = springDataRepository.findById(category.getId().getValue())
                .orElseThrow();
        entity.setName(category.getName());
        return mapper.mapToDomainEntity(springDataRepository.save(entity));
    }
}