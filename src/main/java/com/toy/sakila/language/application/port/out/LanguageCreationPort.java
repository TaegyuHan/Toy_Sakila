package com.toy.sakila.language.application.port.out;

import com.toy.sakila.language.domain.Language;

public interface LanguageCreationPort {
    Language.LanguageId create(Language language);
}