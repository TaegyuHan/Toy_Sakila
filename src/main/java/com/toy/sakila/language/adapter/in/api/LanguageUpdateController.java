package com.toy.sakila.language.adapter.in.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.language.application.port.in.LanguageUpdateCommand;
import com.toy.sakila.language.application.port.in.LanguageUpdateUseCase;
import com.toy.sakila.language.domain.Language;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film/languages")
public class LanguageUpdateController {

    private final LanguageUpdateUseCase languageUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> languageUpdate(
            @PathVariable Long id,
            @RequestBody LanguageUpdateCommand command
    ) {
        Language domain = languageUpdateUseCase.update(new Language.LanguageId(id), command);

        OutputDTO outputDto = OutputDTO.builder()
                .id(domain.getId().getValue())
                .name(domain.getName())
                .lastUpdate(domain.getLastUpdate())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("Language 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
        String name;
        LocalDateTime lastUpdate;
    }
}