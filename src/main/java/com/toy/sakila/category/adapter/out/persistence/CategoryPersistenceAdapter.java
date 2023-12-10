package com.toy.sakila.category.adapter.out.persistence;


import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.category.application.port.out.CategorySavePort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.PersistenceAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class CategoryPersistenceAdapter
        implements CategorySavePort, CategoryReadPort {

    private final SpringDataCategoryRepository springDataRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findByIdIn(List<Category.CategoryId> ids) {
        return Optional.of(ids)
                .map(mapper::mapToJpaEntityIds)
                .map(springDataRepository::findByIdIn)
                .map(mapper::mapToDomainEntities)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Category.CategoryId id) {
        return springDataRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Category save(Category category) {
        return Optional.of(category)
                .map(mapper::mapToJpaEntity)
                .map(springDataRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}