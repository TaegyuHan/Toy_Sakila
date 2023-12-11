package com.toy.sakila.language.application.port.in;

import com.toy.sakila.common.UseCase;
import com.toy.sakila.language.domain.Language;

@UseCase
public interface LanguageCreationUseCase {
    Language create(LanguageCreationCommand command);
}