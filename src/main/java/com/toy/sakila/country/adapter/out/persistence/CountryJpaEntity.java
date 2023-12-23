package com.toy.sakila.country.adapter.out.persistence;


import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class CountryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;

    @Column(name = "country", length = 50, nullable = false)
    private String country;
}