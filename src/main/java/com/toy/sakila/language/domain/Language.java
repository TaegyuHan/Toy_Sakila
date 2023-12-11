package com.toy.sakila.language.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@RequiredArgsConstructor
public class Language {
    private final Language.LanguageId id;
    private String name;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    @Value
    @AllArgsConstructor
    public static class LanguageId {
        Long value;
    }
}
