package com.toy.sakila.store.application.port.in;

import com.toy.sakila.store.domain.Store;

public interface StoreCreationUseCase {
    Store create(StoreCreationCommand command);
}
