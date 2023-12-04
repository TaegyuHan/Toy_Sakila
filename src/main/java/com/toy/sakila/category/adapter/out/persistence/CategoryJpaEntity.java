package com.toy.sakila.category.adapter.out.persistence;

import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.film.adapter.out.persistence.FilmJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    private Long id;

    @ManyToMany
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Set<FilmJpaEntity> film;

    @Column(name = "name", nullable = false, length = 25)
    private String name;
}