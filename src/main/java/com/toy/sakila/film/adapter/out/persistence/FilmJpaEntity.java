package com.toy.sakila.film.adapter.out.persistence;

import com.toy.sakila.actor.adapter.out.persistence.ActorJpaEntity;
import com.toy.sakila.category.adapter.out.persistence.CategoryJpaEntity;
import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.film.domain.EnumFilmRating;
import com.toy.sakila.film.domain.EnumSpecialFeature;
import com.toy.sakila.language.adapter.out.persistence.LanguageJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "film")
public class FilmJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Short releaseYear;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"),
            foreignKey = @ForeignKey(name = "FK_film_actor_film_id"),
            inverseForeignKey = @ForeignKey(name = "FK_film_actor_actor_id")
    )
    private Set<ActorJpaEntity> actor;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            foreignKey = @ForeignKey(name = "FK_film_category_film_id"),
            inverseForeignKey = @ForeignKey(name = "FK_film_category_category_id")
    )
    private Set<CategoryJpaEntity> category;

    @ManyToOne
    @JoinColumn(
            name = "language_id",
            nullable = false
    )
    private LanguageJpaEntity language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private LanguageJpaEntity originalLanguage;

    @Builder.Default
    @Column(name = "rental_duration", nullable = false)
    private Short rentalDuration = 3;

    @Builder.Default
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate = BigDecimal.valueOf(4.99);

    @Column(name = "length")
    private Short length;

    @Builder.Default
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost = BigDecimal.valueOf(19.99);

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17')")
    private EnumFilmRating rating = EnumFilmRating.G;

    @ElementCollection(targetClass = EnumSpecialFeature.class)
    @Column(name = "special_feature")
    @CollectionTable(
            name = "film_special_features",
            joinColumns = @JoinColumn(name = "film_id"),
            foreignKey = @ForeignKey(name = "FK_film_special_features_film_id")
    )
    private Set<EnumSpecialFeature> specialFeatures;
}