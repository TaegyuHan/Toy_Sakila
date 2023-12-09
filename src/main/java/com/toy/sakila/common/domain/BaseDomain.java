package com.toy.sakila.common.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDomain {
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdate;
}