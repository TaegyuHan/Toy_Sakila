package com.toy.sakila.store.adapter.in.web.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.in.StoreCreationCommand;
import com.toy.sakila.store.application.port.in.StoreCreationUseCase;
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
@RequestMapping("/api/v1/store")
public class StoreCreationController {

    private final StoreCreationUseCase storeCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> storeCreation(
            @RequestBody StoreCreationCommand command
    ){
        Store domain = storeCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Store 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Byte id,
            Staff managerStaff,
            LocalDateTime updateDate,
            LocalDateTime createdDate
    ){
        public static OutputDTO of(Store domain){
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .managerStaff(domain.getManagerStaff())
                    .updateDate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}