package com.toy.sakila.customer.adapter.in.web.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.application.port.in.CustomerCreationCommand;
import com.toy.sakila.customer.application.port.in.CustomerCreationUseCase;
import com.toy.sakila.customer.domain.Customer;
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
@RequestMapping("/api/v1/customers")
public class CustomerCreationController {

    private final CustomerCreationUseCase customerCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> customerCreation(
            @RequestBody CustomerCreationCommand command
    ){
        Customer domain = customerCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Customer 생성을 완료했습니다.")
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