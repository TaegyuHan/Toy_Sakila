package com.toy.sakila.language.application.service;


import com.toy.sakila.language.application.port.in.LanguageUpdateCommand;
import com.toy.sakila.language.application.port.in.LanguageUpdateUseCase;
import com.toy.sakila.language.application.port.out.LanguageUpdatePort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageUpdateService implements LanguageUpdateUseCase {

    private final LanguageUpdatePort languageUpdatePort;

    @Override
    public Language update(Language.LanguageId id, LanguageUpdateCommand command) {
        Language language = Language.builder()
                .id(id)
                .name(command.getName())
                .build();
        return languageUpdatePort.update(language);
    }
}