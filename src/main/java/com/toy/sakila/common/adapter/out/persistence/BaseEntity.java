package com.toy.sakila.common.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "create_date", nullable = true, updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @PrePersist
    public void onPersist() {
        createDate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}