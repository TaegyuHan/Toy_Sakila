package com.toy.sakila.language.adapter.in.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationUseCase;
import com.toy.sakila.language.domain.Language;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film/language")
public class LanguageCreationController {

    private final LanguageCreationUseCase languageCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> languageCreation(
            @RequestBody LanguageCreationCommand command
    ) {
        Language domain = languageCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Language 생성을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Builder
    private record OutputDTO(
            Long id,
            String name,
            LocalDateTime lastUpdate,
            LocalDateTime createDate
    ) {
        public static OutputDTO of(Language domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .name(domain.getName())
                    .lastUpdate(domain.getLastUpdate())
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}