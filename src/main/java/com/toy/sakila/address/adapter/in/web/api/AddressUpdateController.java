package com.toy.sakila.address.adapter.in.web.api;


import com.toy.sakila.address.application.port.in.AddressUpdateCommand;
import com.toy.sakila.address.application.port.in.AddressUpdateUseCase;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/address")
public class AddressUpdateController {

    private final AddressUpdateUseCase addressUpdateUseCase;

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> addressUpdate(
            @PathVariable Short id,
            @RequestBody AddressUpdateCommand command
    ) {
        Address domain = addressUpdateUseCase.update(Address.AddressId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Address 수정을 완료했습니다.")
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
            LocalDateTime createDate
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
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}