package com.toy.sakila.language.application.port.out;

import com.toy.sakila.language.domain.Language;

public interface LanguageSavePort {
    Language save(Language language);
}