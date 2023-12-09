package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.category.application.port.out.CategoryCreationPort;
import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.category.application.port.out.CategoryUpdatePort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.PersistenceAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
class CategoryPersistenceAdapter
        implements CategoryCreationPort, CategoryUpdatePort, CategoryReadPort {

    private final SpringDataCategoryRepository springDataRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    @Transactional
    public Category.CategoryId create(Category category) {
        CategoryJpaEntity entity = springDataRepository.save(mapper.mapToJpaEntity(category));
        return Category.CategoryId.of(entity.getId());
    }

    @Override
    public Category update(Category category) {
        CategoryJpaEntity entity = springDataRepository.findById(category.getId().getValue())
                .orElseThrow();
        entity.setName(category.getName());
        return mapper.mapToDomainEntity(springDataRepository.save(entity));
    }

    @Override
    public List<Category> findByIdIn(List<Long> ids) {
        return springDataRepository.findByIdIn(ids).stream()
                .map(mapper::mapToDomainEntity)
                .toList();
    }
}