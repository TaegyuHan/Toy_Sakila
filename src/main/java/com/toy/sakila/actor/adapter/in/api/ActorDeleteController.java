package com.toy.sakila.actor.adapter.in.api;


import com.toy.sakila.actor.application.port.in.ActorDeleteUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.toy.sakila.config.Version.API_PREFIX;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping(API_PREFIX + "/film/actor")
public class ActorDeleteController {

    private final ActorDeleteUseCase actorDeleteUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> actorCreation(
            @PathVariable Short id
    ) {
        actorDeleteUseCase.delete(Actor.ActorId.of(id));

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(id))
                .message("Actor 삭제을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Short id
    ) {
        public static OutputDTO of(Short id) {
            return OutputDTO.builder()
                    .id(id)
                    .build();
        }
    }
}