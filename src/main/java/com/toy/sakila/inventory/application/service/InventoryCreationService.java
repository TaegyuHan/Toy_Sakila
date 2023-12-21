package com.toy.sakila.inventory.application.service;

import com.toy.sakila.film.application.port.out.FilmReadPort;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.inventory.application.port.in.InventoryCreationCommand;
import com.toy.sakila.inventory.application.port.in.InventoryCreationUseCase;
import com.toy.sakila.inventory.application.port.out.InventorySavePort;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class InventoryCreationService implements InventoryCreationUseCase {

    private final FilmReadPort filmReadPort;
    private final StoreReadPort storeReadPort;
    private final InventorySavePort inventorySavePort;

    @Override
    public Inventory create(InventoryCreationCommand command) {
        Film film = filmReadPort.findById(Film.FilmId.of(command.getFilmId()));
        Store store = storeReadPort.findById(Store.StoreId.of(command.getStoreId()));

        Inventory inventory = Inventory.builder()
                .film(film)
                .store(store)
                .build();

        return inventorySavePort.save(inventory);
    }
}
