package com.toy.sakila.inventory.adapter.out.persistence;

import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.film.adapter.out.persistence.FilmJpaEntity;
import com.toy.sakila.store.adapter.out.persistence.StoreJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
public class InventoryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private FilmJpaEntity film;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", insertable = false, updatable = false)
    private StoreJpaEntity store;
}
