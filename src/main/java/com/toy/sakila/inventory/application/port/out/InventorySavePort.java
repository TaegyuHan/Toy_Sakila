package com.toy.sakila.inventory.application.port.out;

import com.toy.sakila.inventory.domain.Inventory;

public interface InventorySavePort {
    Inventory save(Inventory inventory);
}
