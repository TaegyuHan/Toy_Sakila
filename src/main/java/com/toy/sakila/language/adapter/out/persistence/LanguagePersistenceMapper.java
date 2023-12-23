package com.toy.sakila.language.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.language.domain.Language;


@Mapper
public class LanguagePersistenceMapper {
    public LanguageJpaEntity mapToJpaEntity(Language category) {
        return LanguageJpaEntity.builder()
                .languageId(category.getId().getValue())
                .name(category.getName())
                .build();
    }

    public Language mapToDomainEntity(LanguageJpaEntity entity) {
        return Language.builder()
                .id(Language.LanguageId.of(entity.getLanguageId()))
                .name(entity.getName())
                .lastUpdate(entity.getLastUpdate())
                .createDate(entity.getCreateDate())
                .build();
    }
}