package com.toy.sakila.category.domain;


import lombok.*;

import java.time.LocalDateTime;


@Value
@Getter
@Builder
@RequiredArgsConstructor
public class Category {

    private CategoryId id;

    @NonNull
    private final String name;
    private final LocalDateTime lastUpdate;

    @Value
    @AllArgsConstructor
    public static class CategoryId {
        private final Long value;
    }
}