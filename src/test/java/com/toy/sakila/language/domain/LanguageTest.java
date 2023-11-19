package com.toy.sakila.language.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {
    @Test
    @DisplayName("성공 | Domaim | Language | 생성")
    public void testCreateLanguage() {
        // when
        Language category = Language.builder()
                .id(new Language.LanguageId(1L))
                .name("Animation")
                .lastUpdate(LocalDateTime.parse("2023-11-18T13:45:00"))
                .build();

        // then
        assertEquals(1L, category.getId().getValue());
        assertEquals("Animation", category.getName());
        assertEquals(LocalDateTime.parse("2023-11-18T13:45:00"), category.getLastUpdate());
    }
}