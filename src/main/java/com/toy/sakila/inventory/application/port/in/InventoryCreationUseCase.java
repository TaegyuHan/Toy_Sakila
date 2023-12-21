package com.toy.sakila.inventory.application.port.in;

import com.toy.sakila.inventory.domain.Inventory;

public interface InventoryCreationUseCase {
    Inventory create(InventoryCreationCommand command);
}
