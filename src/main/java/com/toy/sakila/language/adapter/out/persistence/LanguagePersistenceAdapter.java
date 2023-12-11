package com.toy.sakila.language.adapter.out.persistence;


import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.language.application.port.out.LanguageReadPort;
import com.toy.sakila.language.application.port.out.LanguageSavePort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@PersistenceAdapter
@RequiredArgsConstructor
public class LanguagePersistenceAdapter
        implements LanguageSavePort, LanguageReadPort {

    private final SpringDataLanguageRepository springDataRepository;
    private final LanguagePersistenceMapper mapper;

    @Override
    public Language findById(Language.LanguageId id) {
        return springDataRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Language save(Language language) {
        return Optional.of(language)
                .map(mapper::mapToJpaEntity)
                .map(springDataRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}