package com.toy.sakila.staff.adapter.in.web.api;

import com.toy.sakila.address.domain.Address;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.staff.application.port.in.StaffUpdateCommand;
import com.toy.sakila.staff.application.port.in.StaffUpdateUseCase;
import com.toy.sakila.store.domain.Store;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/staff")
public class StaffUpdateController {

    private final StaffUpdateUseCase staffUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> staffUpdate(
            @PathVariable Byte id,
            @RequestBody StaffUpdateCommand command
    ){
        Staff domain = staffUpdateUseCase.update(Staff.StaffId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Staff 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
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
            LocalDateTime createdDate
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
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}