package com.toy.sakila.category.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class Category {

    private CategoryId id;
    @NonNull
    private String name;
    private LocalDateTime lastUpdate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class CategoryId {
        Long value;
    }
}