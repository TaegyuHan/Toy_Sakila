package com.toy.sakila.address.adapter.in.web.api;


import com.toy.sakila.address.application.port.in.AddressCreationCommand;
import com.toy.sakila.address.application.port.in.AddressCreationUseCase;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
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
@RequestMapping("/address")
public class AddressCreationController {

    private final AddressCreationUseCase addressCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> addressCreation(
            @RequestBody AddressCreationCommand command
    ) {
        Address domain = addressCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .data(OutputDTO.of(domain))
                .message("Address 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id,
            String address,
            String address2,
            String district,
            City city,
            String postalCode,
            String phone,
            LocalDateTime lastUpdate,
            LocalDateTime createdDate
    ) {
        public static OutputDTO of(Address domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .address(domain.getAddress())
                    .address2(domain.getAddress2())
                    .district(domain.getDistrict())
                    .city(domain.getCity())
                    .postalCode(domain.getPostalCode())
                    .phone(domain.getPhone())
                    .lastUpdate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}