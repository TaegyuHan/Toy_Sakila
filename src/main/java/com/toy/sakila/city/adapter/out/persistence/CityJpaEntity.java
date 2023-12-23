package com.toy.sakila.city.adapter.out.persistence;


import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.country.adapter.out.persistence.CountryJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "city"
)
public class CityJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Short cityId;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryJpaEntity country;
}