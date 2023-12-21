package com.toy.sakila.inventory.application.service;

import com.toy.sakila.film.application.port.out.FilmReadPort;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.inventory.application.port.in.InventoryUpdateCommand;
import com.toy.sakila.inventory.application.port.in.InventoryUpdateUseCase;
import com.toy.sakila.inventory.application.port.out.InventoryReadPort;
import com.toy.sakila.inventory.application.port.out.InventorySavePort;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryUpdateService implements InventoryUpdateUseCase {

    private final InventoryReadPort inventoryReadPort;
    private final FilmReadPort filmReadPort;
    private final StoreReadPort storeReadPort;
    private final InventorySavePort inventorySavePort;

    @Override
    public Inventory update(Inventory.InventoryId id, InventoryUpdateCommand command) {
        Inventory domain = inventoryReadPort.findById(id);
        Film film = filmReadPort.findById(Film.FilmId.of(command.getFilmId()));
        Store store = storeReadPort.findById(Store.StoreId.of(command.getStoreId()));

        domain.setFilm(film);
        domain.setStore(store);

        return inventorySavePort.save(domain);
    }
}