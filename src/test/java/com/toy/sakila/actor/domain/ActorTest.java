package com.toy.sakila.actor.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

    @Test
    @DisplayName("성공 | 도메인 | Actor | 생성")
    public void testActorDomainCreation() {
        // when
        Actor actor = Actor.builder()
                .id(Actor.ActorId.of(1L))
                .firstName("Test Actor")
                .lastName("Test Actor")
                .lastUpdate(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .lastUpdate(LocalDateTime.of(2021, 8, 1, 0, 0, 0))
                .build();

        // then
        assertNotNull(actor.getId());
        assertEquals("Test Actor", actor.getFirstName());
        assertEquals("Test Actor", actor.getLastName());
        assertEquals(LocalDateTime.of(2021, 8, 1, 0, 0, 0), actor.getLastUpdate());
        assertEquals(LocalDateTime.of(2021, 8, 1, 0, 0, 0), actor.getCreatedDate());
    }
}