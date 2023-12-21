package com.toy.sakila.inventory.application.port.in;

import com.toy.sakila.inventory.domain.Inventory;

public interface InventoryUpdateUseCase {
    Inventory update(Inventory.InventoryId id, InventoryUpdateCommand command);
}
