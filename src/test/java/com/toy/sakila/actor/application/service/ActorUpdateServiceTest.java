package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorUpdateCommand;
import com.toy.sakila.actor.application.port.out.ActorUpdatePort;
import com.toy.sakila.actor.domain.Actor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ActorUpdateServiceTest {

    @InjectMocks
    private ActorUpdateService actorUpdateService;

    @Mock
    private ActorUpdatePort actorUpdatePort;

    @Test
    @DisplayName("성공 | Service | Actor | 수정")
    void update() {
        // given
        ActorUpdateCommand command = ActorUpdateCommand.builder()
                .firstName("TaeGyu")
                .lastName("Han")
                .build();
        Actor.ActorId id = new Actor.ActorId(1L);

        Actor expected = Actor.builder()
                .id(id)
                .firstName("TaeGyuUpdate")
                .lastName("HanUpdate")
                .build();

        given(actorUpdatePort.update(any(Actor.class)))
                .willReturn(expected);

        // when
        Actor result = actorUpdateService.update(id, command);

        // then
        verify(actorUpdatePort, times(1)).update(any(Actor.class));
        assertEquals(expected, result);
    }
}