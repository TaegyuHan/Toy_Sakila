package com.toy.sakila.actor.adapter.in.api;


import com.toy.sakila.actor.application.port.in.ActorCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;


import static com.toy.sakila.config.Version.API_PREFIX;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping(API_PREFIX + "/film/actor")
public class ActorCreationController {

    private final ActorCreationUseCase actorCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> actorCreation(
            @Valid @RequestBody ActorCommand command
    ) {
        Actor domain = actorCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Actor 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id,
            String firstName,
            String lastName,
            LocalDateTime lastUpdate,
            LocalDateTime createDate
    ) {
        public static OutputDTO of(Actor domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .firstName(domain.getFirstName())
                    .lastName(domain.getLastName())
                    .lastUpdate(domain.getLastUpdate())
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}