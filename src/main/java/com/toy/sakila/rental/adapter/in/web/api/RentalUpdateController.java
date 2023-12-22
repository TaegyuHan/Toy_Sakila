package com.toy.sakila.rental.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.rental.application.port.in.RentalUpdateCommand;
import com.toy.sakila.rental.application.port.in.RentalUpdateUseCase;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.domain.Staff;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/rental")
public class RentalUpdateController {

    private final RentalUpdateUseCase rentalUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> rentalUpdate(
            @PathVariable Integer id,
            @RequestBody RentalUpdateCommand command
    ){
        Rental domain = rentalUpdateUseCase.update(Rental.RentalId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Rental 수정을 완료했습니다.")
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