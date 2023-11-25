package com.toy.sakila.actor.application.service;

import com.toy.sakila.actor.application.port.in.ActorCreationCommand;
import com.toy.sakila.actor.application.port.out.ActorCreationPort;
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
import static org.mockito.Mockito.verify;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ActorCreationServiceTest {

    @InjectMocks
    private ActorCreationService actorCreationService;

    @Mock
    private ActorCreationPort actorCreationPort;

    @Test
    @DisplayName("성공 | Service | Actor | 생성")
    void create() {
        // given
        ActorCreationCommand command = ActorCreationCommand.builder()
                .firstName("TaeGyu")
                .lastName("Han")
                .build();

        Actor.ActorId expected = new Actor.ActorId(1L);

        given(actorCreationPort.create(any(Actor.class)))
                .willReturn(expected);

        // when
        Actor.ActorId result = actorCreationService.create(command);

        // then
        assertEquals(expected, result);
        verify(actorCreationPort).create(any(Actor.class));
    }
}