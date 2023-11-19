package com.toy.sakila.language.application.service;


import com.toy.sakila.language.application.port.in.LanguageUpdateCommand;
import com.toy.sakila.language.application.port.out.LanguageUpdatePort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class LanguageUpdateServiceTest {

    @InjectMocks
    private LanguageUpdateService languageUpdateService;

    @Mock
    private LanguageUpdatePort languageUpdatePort;

    @Test
    @DisplayName("성공 | Service | Language | 수정")
    void update() {
        // given
        LanguageUpdateCommand command = LanguageUpdateCommand.builder()
                .name("Animation")
                .build();

        Language.LanguageId id = new Language.LanguageId(1L);

        Language expected = Language.builder()
                .id(id)
                .name("Animation")
                .build();

        given(languageUpdatePort.update(any(Language.class)))
                .willReturn(expected);

        // when
        Language result = languageUpdateService.update(id, command);

        // then
        verify(languageUpdatePort, times(1))
                .update(any(Language.class));
        assertEquals(expected, result);
    }
}