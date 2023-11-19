package com.toy.sakila.language.adapter.out.persistence;


import com.toy.sakila.common.DataJpaUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaUnitTest
class LanguageJpaEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("성공 | JPA Entity | Language | 생성")
    public void testLanguageJpaEntityPersistence() {
        // when
        LanguageJpaEntity category = LanguageJpaEntity.builder()
                .name("Test Language")
                .build();
        category = entityManager.persistFlushFind(category);

        // that
        assertNotNull(category.getId());
        assertEquals("Test Language", category.getName());
    }
}