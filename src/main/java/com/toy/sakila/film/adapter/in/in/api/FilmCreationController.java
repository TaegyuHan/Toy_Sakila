package com.toy.sakila.film.adapter.in.in.api;

import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.film.application.port.in.FilmCreationCommand;
import com.toy.sakila.film.application.port.in.FilmCreationUseCase;
import com.toy.sakila.film.domain.EnumFilmRating;
import com.toy.sakila.film.domain.EnumSpecialFeature;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.language.domain.Language;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


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
        Film domain = filmCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Film 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id,
            String title,
            String description,
            Short releaseYear,
            List<Actor> actors,
            List<Category> categories,
            Language language,
            Language originalLanguage,
            Short rentalDuration,
            BigDecimal rentalRate,
            Short length,
            BigDecimal replacementCost,
            EnumFilmRating rating,
            List<EnumSpecialFeature> specialFeatures
    ) {
        public static OutputDTO of(Film domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .title(domain.getTitle())
                    .description(domain.getDescription())
                    .releaseYear(domain.getReleaseYear())
                    .actors(domain.getActors())
                    .categories(domain.getCategories())
                    .language(domain.getLanguage())
                    .originalLanguage(domain.getOriginalLanguage())
                    .rentalDuration(domain.getRentalDuration())
                    .rentalRate(domain.getRentalRate())
                    .length(domain.getLength())
                    .replacementCost(domain.getReplacementCost())
                    .rating(domain.getRating())
                    .specialFeatures(domain.getSpecialFeatures())
                    .build();
        }
    }
}