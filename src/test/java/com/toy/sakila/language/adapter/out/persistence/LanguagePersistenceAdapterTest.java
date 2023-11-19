package com.toy.sakila.language.adapter.out.persistence;


import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class LanguagePersistenceAdapterTest {

    @Mock
    private SpringDataLanguageRepository springDataRepository;
    @Mock
    private LanguagePersistenceMapper mapper;

    @InjectMocks
    private LanguagePersistenceAdapter languagePersistenceAdapter;

    @Test
    @DisplayName("성공 | PersistenceAdapter | Language | 생성")
    void create() {
        // given
        Language language = Language.builder()
                .name("English")
                .build();

        LanguageJpaEntity expected = LanguageJpaEntity.builder()
                .id(1L)
                .name("English")
                .build();

        // when
        when(springDataRepository.save(mapper.mapToJpaEntity(language)))
                .thenReturn(expected);

        Language.LanguageId result = languagePersistenceAdapter.create(language);

        // then
        assertEquals(expected.getId(), result.getValue());
    }
}