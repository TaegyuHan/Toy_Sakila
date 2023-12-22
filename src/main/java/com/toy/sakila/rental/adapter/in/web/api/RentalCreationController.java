package com.toy.sakila.rental.adapter.in.web.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.rental.application.port.in.RentalCreationCommand;
import com.toy.sakila.rental.application.port.in.RentalCreationUseCase;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.domain.Staff;
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
@RequestMapping("/api/v1/rental")
public class RentalCreationController {

    private final RentalCreationUseCase inventoryCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> inventoryCreation(
            @RequestBody RentalCreationCommand command
    ){
        Rental domain = inventoryCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Rental 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Integer rentalId,
            LocalDateTime rentalDate,
            LocalDateTime returnDate,
            Staff staff,
            Inventory inventory,
            Customer customer,
            LocalDateTime lastUpdate,
            LocalDateTime createdDate
    ){
        private static OutputDTO of(Rental domain) {
            return OutputDTO.builder()
                    .rentalId(domain.getId().getValue())
                    .rentalDate(domain.getRentalDate())
                    .returnDate(domain.getReturnDate())
                    .staff(domain.getStaff())
                    .inventory(domain.getInventory())
                    .customer(domain.getCustomer())
                    .lastUpdate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}