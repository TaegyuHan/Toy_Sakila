package com.toy.sakila.store.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.in.StoreUpdateCommand;
import com.toy.sakila.store.application.port.in.StoreUpdateUseCase;
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
@RequestMapping("/api/v1/store")
public class StoreUpdateController {

    private final StoreUpdateUseCase storeUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> storeUpdate(
            @PathVariable Byte id,
            @RequestBody StoreUpdateCommand command
    ){
        Store domain = storeUpdateUseCase.update(Store.StoreId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Store 수정을 완료했습니다.")
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