package com.toy.sakila.language.application.port.out;

import com.toy.sakila.language.domain.Language;

public interface LanguageReadPort {
    Language findById(Language.LanguageId id);
}