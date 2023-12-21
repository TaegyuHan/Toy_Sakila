package com.toy.sakila.inventory.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.film.adapter.out.persistence.FilmPersistenceMapper;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.store.adapter.out.persistence.StorePersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class InventoryPersistenceMapper {

    private final FilmPersistenceMapper filmPersistenceMapper;
    private final StorePersistenceMapper storePersistenceMapper;

    public InventoryJpaEntity mapToJpaEntity(Inventory domain) {
        return InventoryJpaEntity.builder()
                .inventoryId(domain.getId().getValue())
                .film(filmPersistenceMapper.mapToJpaEntity(domain.getFilm()))
                .store(storePersistenceMapper.mapToJpaEntity(domain.getStore()))
                .build();
    }

    public Inventory mapToDomainEntity(InventoryJpaEntity entity) {
        return Inventory.builder()
                .id(Inventory.InventoryId.of(entity.getInventoryId()))
                .film(filmPersistenceMapper.mapToDomainEntity(entity.getFilm()))
                .store(storePersistenceMapper.mapToDomainEntity(entity.getStore()))
                .build();
    }
}