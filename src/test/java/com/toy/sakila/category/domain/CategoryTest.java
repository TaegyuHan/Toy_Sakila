package com.toy.sakila.category.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    @DisplayName("성공 | Domaim | Category | 생성")
    public void testCreateCategory() {
        // when
        Category category = Category.builder()
                .id(Category.CategoryId.of(1L))
                .name("Animation")
                .lastUpdate(LocalDateTime.parse("2023-11-18T13:45:00"))
                .build();

        // then
        assertEquals(1L, category.getId().getValue());
        assertEquals("Animation", category.getName());
        assertEquals(LocalDateTime.parse("2023-11-18T13:45:00"), category.getLastUpdate());
    }
}