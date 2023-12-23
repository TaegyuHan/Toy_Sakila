package com.toy.sakila.staff.adapter.in.web.api;


import com.toy.sakila.address.domain.Address;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.staff.application.port.in.StaffCreationCommand;
import com.toy.sakila.staff.application.port.in.StaffCreationUseCase;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.domain.Store;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Blob;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/staff")
public class StaffCreationController {

    private final StaffCreationUseCase staffCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> staffCreation(
            @RequestBody StaffCreationCommand command
    ){
        Staff domain = staffCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Staff 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Byte staffId,
            String firstName,
            String lastName,
            Address address,
            Blob picture,
            String email,
            Store store,
            boolean active,
            String username,
            String password,
            LocalDateTime lastUpdate,
            LocalDateTime createDate
    ){
        private static OutputDTO of(Staff domain) {
            return OutputDTO.builder()
                    .staffId(domain.getId().getValue())
                    .firstName(domain.getFirstName())
                    .lastName(domain.getLastName())
                    .address(domain.getAddress())
                    .picture(domain.getPicture())
                    .email(domain.getEmail())
                    .store(domain.getStore())
                    .active(domain.isActive())
                    .username(domain.getUsername())
                    .password(domain.getPassword())
                    .lastUpdate(domain.getLastUpdate())
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}