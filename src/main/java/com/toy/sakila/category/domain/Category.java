package com.toy.sakila.category.domain;


import com.toy.sakila.common.domain.BaseDomain;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @Setter
@SuperBuilder
@RequiredArgsConstructor
public class Category extends BaseDomain {

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