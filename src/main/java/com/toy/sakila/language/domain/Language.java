package com.toy.sakila.language.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class Language {
    private final LanguageId id;
    private String name;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class LanguageId {
        Byte value;
    }
}
