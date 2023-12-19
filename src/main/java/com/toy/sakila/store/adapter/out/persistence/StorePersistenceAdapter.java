package com.toy.sakila.store.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.application.port.out.StoreSavePort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class StorePersistenceAdapter implements StoreReadPort, StoreSavePort {

    private final SpringDataStoreRepository springDataStoreRepository;
    private final StorePersistenceMapper mapper;

    @Override
    public Store findById(Store.StoreId storeId) {
        return springDataStoreRepository.findById(storeId.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Store save(Store store) {
        return Optional.of(store)
                .map(mapper::mapToJpaEntity)
                .map(springDataStoreRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}