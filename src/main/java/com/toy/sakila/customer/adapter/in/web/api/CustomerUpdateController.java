package com.toy.sakila.customer.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.application.port.in.CustomerUpdateCommand;
import com.toy.sakila.customer.application.port.in.CustomerUpdateUseCase;
import com.toy.sakila.customer.domain.Customer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerUpdateController {

    private final CustomerUpdateUseCase customerUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> customerUpdate(
            @PathVariable String id,
            @RequestBody CustomerUpdateCommand command
    ){
        Customer domain = customerUpdateUseCase.update(Customer.CustomerId.of(Short.valueOf(id)), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Customer 수정을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id,
            String firstName,
            String lastName,
            String email,
            Short addressId,
            boolean active,
            LocalDateTime createdDate,
            LocalDateTime updateDate
    ){
        public static OutputDTO of(Customer domain){
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .firstName(domain.getFirstName())
                    .lastName(domain.getLastName())
                    .email(domain.getEmail())
                    .addressId(domain.getAddress().getId().getValue())
                    .active(domain.isActive())
                    .createdDate(domain.getCreatedDate())
                    .updateDate(domain.getLastUpdate())
                    .build();
        }
    }
}