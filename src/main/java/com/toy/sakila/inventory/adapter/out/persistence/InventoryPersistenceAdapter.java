package com.toy.sakila.inventory.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.inventory.application.port.out.InventoryReadPort;
import com.toy.sakila.inventory.application.port.out.InventorySavePort;
import com.toy.sakila.inventory.domain.Inventory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class InventoryPersistenceAdapter implements InventorySavePort, InventoryReadPort {

    private final InventorySpringDataJpaRepository inventorySpringDataJpaRepository;
    private final InventoryPersistenceMapper mapper;

    @Override
    public Inventory save(Inventory domain) {
        return Optional.of(domain)
                .map(mapper::mapToJpaEntity)
                .map(inventorySpringDataJpaRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Inventory findById(Inventory.InventoryId id) {
        return inventorySpringDataJpaRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}