package com.toy.sakila.inventory.domain;

import com.toy.sakila.film.domain.Film;
import com.toy.sakila.store.domain.Store;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Inventory {
    private InventoryId id;
    private Film film;
    private Store store;
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class InventoryId {
        Integer value;
    }
}
