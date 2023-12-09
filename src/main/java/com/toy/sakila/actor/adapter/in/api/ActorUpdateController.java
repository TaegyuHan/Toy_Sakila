package com.toy.sakila.actor.adapter.in.api;

import com.toy.sakila.actor.application.port.in.ActorUpdateUseCase;
import com.toy.sakila.actor.application.port.in.ActorUpdateCommand;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film/actor")
public class ActorUpdateController {

    private final ActorUpdateUseCase actorUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> actorUpdate(
            @Validated @PathVariable Long id,
            @Validated @RequestBody ActorUpdateCommand command
    ) {
        Actor domain = actorUpdateUseCase.update(Actor.ActorId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Actor 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
        String firstName;
        String lastName;
        LocalDateTime lastUpdate;

        public static OutputDTO of(Actor domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .firstName(domain.getFirstName())
                    .lastName(domain.getLastName())
                    .lastUpdate(domain.getLastUpdate())
                    .build();
        }
    }
}