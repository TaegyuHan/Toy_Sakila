package com.toy.sakila.language.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.language.domain.Language;

@Mapper
public class LanguagePersistenceMapper {
    public LanguageJpaEntity mapToJpaEntity(Language category) {
        return LanguageJpaEntity.builder()
                .id(category.getId().getValue())
                .name(category.getName())
                .build();
    }
}