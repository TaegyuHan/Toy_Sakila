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

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/city")
public class CityUpdateController {

    private final CityUpdateUseCase countryUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> countryUpdate(
            @PathVariable Long id,
            @RequestBody CityUpdateCommand command
    ) {
        City city = countryUpdateUseCase.update(City.CityId.of(id), command);

        CityUpdateController.OutputDTO outputDto = OutputDTO.builder()
                .id(city.getCityId().getValue())
                .city(city.getCity())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("City 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
        String city;
    }
}