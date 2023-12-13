package com.toy.sakila.store.application.port.out;

import com.toy.sakila.store.domain.Store;

public interface StoreReadPort {
    Store findById(Store.StoreId storeId);
}
