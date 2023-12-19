package com.toy.sakila.store.application.port.in;

import com.toy.sakila.store.domain.Store;

public interface StoreUpdateUseCase {
    Store update(Store.StoreId id, StoreUpdateCommand command);
}
