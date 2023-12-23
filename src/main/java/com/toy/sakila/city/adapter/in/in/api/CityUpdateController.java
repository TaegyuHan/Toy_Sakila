package com.toy.sakila.city.adapter.in.in.api;

import com.toy.sakila.city.application.port.in.CityUpdateCommand;
import com.toy.sakila.city.application.port.in.CityUpdateUseCase;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/city")
public class CityUpdateController {

    private final CityUpdateUseCase countryUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> countryUpdate(
            @PathVariable Short id,
            @RequestBody CityUpdateCommand command
    ) {
        City domain = countryUpdateUseCase.update(City.CityId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("City 수정을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id,
            String city,
            Long countryId,
            LocalDateTime lastUpdate,
            LocalDateTime createDate
    ) {
        public static OutputDTO of(City domain) {
            return OutputDTO.builder()
                    .id(domain.getCityId().getValue())
                    .city(domain.getCity())
                    .lastUpdate(domain.getLastUpdate())
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}