package com.toy.sakila.language.application.port.out;


import com.toy.sakila.language.domain.Language;

public interface LanguageUpdatePort {
    Language update(Language category);
}