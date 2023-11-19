package com.toy.sakila.language.adapter.out.persistence;


import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.language.application.port.out.LanguageCreationPort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LanguagePersistenceAdapter implements LanguageCreationPort {

    private final SpringDataLanguageRepository springDataRepository;
    private final LanguagePersistenceMapper mapper;

    @Override
    public Language.LanguageId create(Language language) {
        LanguageJpaEntity entity = springDataRepository.save(mapper.mapToJpaEntity(language));
        return new Language.LanguageId(entity.getId());
    }
}