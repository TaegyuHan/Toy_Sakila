package com.toy.sakila.language.domain;


import lombok.*;

import java.time.LocalDateTime;

@Value
@Getter
@Builder
@RequiredArgsConstructor
public class Language {
    private Language.LanguageId id;

    @NonNull
    private final String name;
    private final LocalDateTime lastUpdate;

    @Value
    @AllArgsConstructor
    public static class LanguageId {
        private final Long value;
    }
}
