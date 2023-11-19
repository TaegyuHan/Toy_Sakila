package com.toy.sakila.language.adapter.out.persistence;


import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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

    @Test
    @DisplayName("성공 | PersistenceAdapter | Language | 수정")
    void update() {
        // given
        Language language = Language.builder()
                .id(new Language.LanguageId(1L))
                .name("Updated English")
                .build();

        LanguageJpaEntity entity = LanguageJpaEntity.builder()
                .id(1L)
                .name("English")
                .build();

        Language updatedLanguage  = Language.builder()
                .id(new Language.LanguageId(1L))
                .name("Updated English")
                .build();

        given(springDataRepository.findById(anyLong()))
                .willReturn(Optional.of(entity));
        given(springDataRepository.save(any(LanguageJpaEntity.class)))
                .willReturn(entity);
        given(mapper.mapToDomainEntity(any(LanguageJpaEntity.class)))
                .willReturn(updatedLanguage);

        // when
        Language result = languagePersistenceAdapter.update(language);

        // then
        assertEquals(updatedLanguage, result);
        verify(springDataRepository).save(entity);
    }
}