package com.toy.sakila.category.adapter.out.persistence;


import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.category.application.port.out.CategorySavePort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.PersistenceAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

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
    public List<Category> findByIdIn(List<Long> ids) {
        return springDataRepository.findByIdIn(ids).stream()
                .map(mapper::mapToDomainEntity)
                .toList();
    }

    @Override
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