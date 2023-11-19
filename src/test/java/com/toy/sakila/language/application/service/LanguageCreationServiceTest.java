package com.toy.sakila.language.application.service;

import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.out.LanguageCreationPort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class LanguageCreationServiceTest {

    @InjectMocks
    private LanguageCreationService categoryCreationService;

    @Mock
    private LanguageCreationPort categoryCreationPort;

    @Test
    @DisplayName("성공 | Service | Language | 생성")
    void create() {
        // given
        LanguageCreationCommand command = LanguageCreationCommand.builder()
                .name("Animation")
                .build();
        Language.LanguageId expected = new Language.LanguageId(1L);

        // when
        when(categoryCreationService.create(command))
                .thenReturn(expected);
        Language.LanguageId result = categoryCreationService.create(command);

        // then
        assertEquals(expected, result);
        verify(categoryCreationPort).create(any(Language.class));
    }
}