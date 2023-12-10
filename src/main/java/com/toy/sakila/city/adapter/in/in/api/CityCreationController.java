package com.toy.sakila.city.adapter.in.in.api;

import com.toy.sakila.city.application.port.in.CityCreationUseCase;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.city.application.port.in.CityCreationCommand;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/city")
public class CityCreationController {

    private final CityCreationUseCase cityCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> cityCreation(
            @RequestBody CityCreationCommand command
    ){
        City domain = cityCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("City 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Getter @Setter
    @Builder
    public static class OutputDTO {
        Long id;
        String city;
        Long countryId;
        LocalDateTime lastUpdate;

        public static OutputDTO of(City city) {
            return OutputDTO.builder()
                    .id(city.getCityId().getValue())
                    .city(city.getCity())
                    .lastUpdate(city.getLastUpdate())
                    .build();
        }
    }
}