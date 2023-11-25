package com.toy.sakila.actor.adapter.in.api;

import com.toy.sakila.actor.application.port.in.ActorCreationCommand;
import com.toy.sakila.actor.application.port.in.ActorCreationUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
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
@RequestMapping("/film/actor")
public class ActorCreationController {

    private final ActorCreationUseCase actorCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> actorCreation(
            @RequestBody ActorCreationCommand command
    ) {
        Actor.ActorId id = actorCreationUseCase.create(command);

        OutputDTO outputDto = OutputDTO.builder()
                .id(id.getValue())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("Actor 생성을 완료했습니다.")
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