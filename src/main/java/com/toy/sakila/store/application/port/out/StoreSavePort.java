package com.toy.sakila.store.application.port.out;

import com.toy.sakila.store.domain.Store;

public interface StoreSavePort {
    Store save(Store store);
}
