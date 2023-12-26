package com.toy.sakila.actor.adapter.in.api;


import com.toy.sakila.actor.application.port.in.ActorReadUseCase;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.toy.sakila.config.Version.API_PREFIX;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping(API_PREFIX + "/film/actor")
public class ActorReadController {

    private final ActorReadUseCase actorReadUseCase;

    @GetMapping
    public ResponseEntity<ResponseBody<Object>> actorList() {
        List<Actor> actors = actorReadUseCase.findAll();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(actors))
                .message("Actor 목록 조회를 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            List<Actor> actors
    ) {
        public static OutputDTO of(List<Actor> actors) {
            return OutputDTO.builder()
                    .actors(actors)
                    .build();
        }
    }
}