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
        Language.LanguageId id = languageCreationUseCase.create(command);

        OutputDTO outputDto = OutputDTO.builder()
                .id(id.getValue())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("Language 생성을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
    }
}