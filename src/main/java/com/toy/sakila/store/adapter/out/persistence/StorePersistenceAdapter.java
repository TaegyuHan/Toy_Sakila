package com.toy.sakila.store.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class StorePersistenceAdapter implements StoreReadPort {

    private final SpringDataStoreRepository springDataStoreRepository;
    private final StorePersistenceMapper mapper;

    @Override
    public Store findById(Store.StoreId storeId) {
        return springDataStoreRepository.findById(storeId.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}