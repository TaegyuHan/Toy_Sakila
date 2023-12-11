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
        // when
        ActorJpaEntity category = ActorJpaEntity.builder()
                .firstName("Test Actor")
                .lastName("Test Actor")
                .build();
        category = entityManager.persistFlushFind(category);

        // then
        assertNotNull(category.getId());
        assertEquals("Test Actor", category.getFirstName());
        assertEquals("Test Actor", category.getLastName());
    }
}