package com.toy.sakila.inventory.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class InventoryCreationCommand {
    Long filmId;
    Byte storeId;
}
