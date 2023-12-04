package com.toy.sakila.film.adapter.in.in.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.film.application.port.in.FilmCreationCommand;
import com.toy.sakila.film.application.port.in.FilmCreationUseCase;
import com.toy.sakila.film.domain.Film;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film")
public class FilmCreationController {

    private final FilmCreationUseCase filmCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> filmCreation(
            @RequestBody FilmCreationCommand command
    ){
        Film.FilmId id = filmCreationUseCase.create(command);

        OutputDTO outputDTO = OutputDTO.builder()
                .id(id.getValue())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(outputDTO)
                .message("Film 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Value
    @Getter @Setter
    @Builder
    public static class OutputDTO {
        Long id;
    }
}