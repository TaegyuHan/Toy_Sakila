package com.toy.sakila.category.adapter.out.persistence;


import com.toy.sakila.common.DataJpaUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaUnitTest
class CategoryJpaEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("성공 | JPA Entity | Category | 생성")
    public void categoryJpaEntityPersistenceTest() {
        // given
        CategoryJpaEntity category = CategoryJpaEntity.builder()
                .name("Test Category")
                .build();

        // when
        category = entityManager.persistFlushFind(category);

        // then
        assertNotNull(category.getId());
        assertEquals("Test Category", category.getName());
    }
}