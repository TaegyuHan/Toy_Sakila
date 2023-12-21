package com.toy.sakila.inventory.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.inventory.application.port.in.InventoryUpdateCommand;
import com.toy.sakila.inventory.application.port.in.InventoryUpdateUseCase;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.store.domain.Store;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/staff")
public class InventoryUpdateController {

    private final InventoryUpdateUseCase staffUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> staffUpdate(
            @PathVariable Integer id,
            @RequestBody InventoryUpdateCommand command
    ){
        Inventory domain = staffUpdateUseCase.update(Inventory.InventoryId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Inventory 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
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