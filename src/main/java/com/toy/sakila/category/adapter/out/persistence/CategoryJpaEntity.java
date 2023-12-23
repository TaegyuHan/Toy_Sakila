package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Byte categoryId;

    @Column(name = "name", nullable = false, length = 25)
    private String name;
}