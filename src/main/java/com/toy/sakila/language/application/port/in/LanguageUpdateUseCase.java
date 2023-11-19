package com.toy.sakila.language.application.port.in;

import com.toy.sakila.language.domain.Language;

public interface LanguageUpdateUseCase {
    Language update(Language.LanguageId languageId, LanguageUpdateCommand command);
}