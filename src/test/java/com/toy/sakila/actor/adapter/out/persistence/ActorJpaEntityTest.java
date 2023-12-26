package com.toy.sakila.actor.adapter.out.persistence;

import com.toy.sakila.common.DataJpaUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaUnitTest
class ActorJpaEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("성공 | JPA Entity | Actor | 생성")
    public void testActorJpaEntityPersistence() {
        // given
        ActorJpaEntity expected = ActorJpaEntity.builder()
                .firstName("Test Actor")
                .lastName("Test Actor")
                .build();

        // when
        ActorJpaEntity actual = entityManager.persistFlushFind(expected);

        // then
        assertNotNull(actual.getActorId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }
}