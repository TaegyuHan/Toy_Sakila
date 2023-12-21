package com.toy.sakila.inventory.adapter.in.web.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.inventory.application.port.in.InventoryCreationCommand;
import com.toy.sakila.inventory.application.port.in.InventoryCreationUseCase;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.store.domain.Store;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryCreationController {

    private final InventoryCreationUseCase inventoryCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> inventoryCreation(
            @RequestBody InventoryCreationCommand command
    ){
        Inventory domain = inventoryCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Inventory 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Integer inventoryId,
            Film film,
            Store store,
            LocalDateTime lastUpdate,
            LocalDateTime createdDate
    ){
        private static OutputDTO of(Inventory domain) {
            return OutputDTO.builder()
                    .inventoryId(domain.getId().getValue())
                    .film(domain.getFilm())
                    .store(domain.getStore())
                    .lastUpdate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}