package com.toy.sakila.language.adapter.out.persistence;

import com.toy.sakila.language.domain.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LanguagePersistenceMapperTest {

    @InjectMocks
    private LanguagePersistenceMapper mapper;

    @Mock
    private LanguageJpaEntity languageJpaEntity;

    @Test
    void mapToJpaEntity() {
        // given
        Language language = Language.builder()
                .id(new Language.LanguageId(1L))
                .name("English")
                .lastUpdate(LocalDateTime.parse("2021-09-01T00:00:00"))
                .build();

        // when
        LanguageJpaEntity result = mapper.mapToJpaEntity(language);

        // then
        assertEquals(language.getId().getValue(), result.getId());
        assertEquals(language.getName(), result.getName());
    }

    @Test
    void mapToDomainEntity() {
        // given
        LanguageJpaEntity entity = LanguageJpaEntity.builder()
                .id(1L)
                .name("English")
                .build();

        // when
        Language result = mapper.mapToDomainEntity(entity);

        // then
        assertEquals(entity.getId(), result.getId().getValue());
        assertEquals(entity.getName(), result.getName());
    }
}