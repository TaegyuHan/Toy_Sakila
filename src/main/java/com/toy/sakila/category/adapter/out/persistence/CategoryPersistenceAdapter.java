package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.category.application.port.out.CategoryCreationPort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.PersistenceAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class CategoryPersistenceAdapter
        implements CategoryCreationPort {

    private final SpringDataCategoryRepository springDataRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    @Transactional
    public Category.CategoryId create(Category category) {
        CategoryJpaEntity entity = springDataRepository.save(mapper.mapToJpaEntity(category));
        return new Category.CategoryId(entity.getId());
    }
}