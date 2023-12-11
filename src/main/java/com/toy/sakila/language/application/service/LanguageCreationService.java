package com.toy.sakila.language.application.service;


import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationUseCase;
import com.toy.sakila.language.application.port.out.LanguageSavePort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LanguageCreationService implements LanguageCreationUseCase {

    private final LanguageSavePort languageSavePort;

    @Override
    public Language create(LanguageCreationCommand command) {
        Language language = Language.builder()
                .name(command.getName())
                .build();
        return languageSavePort.save(language);
    }
}