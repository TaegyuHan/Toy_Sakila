package com.toy.sakila.country.adapter.in.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.country.application.port.in.CountryCreationCommand;
import com.toy.sakila.country.application.port.in.CountryCreationUseCase;
import com.toy.sakila.country.domain.Country;
import com.toy.sakila.film.adapter.in.in.api.FilmCreationController;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/country")
public class CountryCreationController {

    private final CountryCreationUseCase countryCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> countryCreation(
            @RequestBody CountryCreationCommand command
    ) {
        Country.CountryId id = countryCreationUseCase.create(command);

        CountryCreationController.OutputDTO outputDTO = CountryCreationController.OutputDTO.builder()
                .id(id.getValue())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(outputDTO)
                .message("Country 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Integer id;
    }
}