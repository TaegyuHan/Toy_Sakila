package com.toy.sakila.language.application.service;


import com.toy.sakila.language.application.port.in.LanguageCreationCommand;
import com.toy.sakila.language.application.port.in.LanguageCreationUseCase;
import com.toy.sakila.language.application.port.out.LanguageCreationPort;
import com.toy.sakila.language.domain.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageCreationService implements LanguageCreationUseCase {

    private final LanguageCreationPort languageCreationPort;

    @Override
    public Language.LanguageId create(LanguageCreationCommand command) {
        Language language = Language.builder()
                .name(command.getName())
                .build();
        return languageCreationPort.create(language);
    }
}