package com.toy.sakila.country.adapter.in.api;

import com.toy.sakila.country.application.port.in.CountryUpdateCommand;
import com.toy.sakila.country.application.port.in.CountryUpdateUseCase;
import com.toy.sakila.country.domain.Country;
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
@RequestMapping("/film/country")
public class CountryUpdateController {

    private final CountryUpdateUseCase countryUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> countryUpdate(
            @PathVariable Short id,
            @RequestBody CountryUpdateCommand command
    ) {
        Country domain = countryUpdateUseCase.update(Country.CountryId.of(id), command);

        CountryUpdateController.OutputDTO outputDto = CountryUpdateController.OutputDTO.builder()
                .id(domain.getId().getValue())
                .name(domain.getCountry())
                .lastUpdate(domain.getLastUpdate())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("Country 수정을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Short id;
        String name;
        LocalDateTime lastUpdate;
    }
}