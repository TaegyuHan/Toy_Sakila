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

    public Language mapToDomainEntity(LanguageJpaEntity entity) {
        return Language.builder()
                .id(Language.LanguageId.of(entity.getId()))
                .name(entity.getName())
                .lastUpdate(entity.getLastUpdate())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}