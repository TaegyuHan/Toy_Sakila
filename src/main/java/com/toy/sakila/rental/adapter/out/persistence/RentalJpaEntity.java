package com.toy.sakila.rental.adapter.out.persistence;

import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.customer.adapter.out.persistence.CustomerJpaEntity;
import com.toy.sakila.inventory.adapter.out.persistence.InventoryJpaEntity;
import com.toy.sakila.staff.adapter.out.persistence.StaffJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class RentalJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "rental_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rentalDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", insertable = false, updatable = false)
    private StaffJpaEntity staff;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", insertable = false, updatable = false)
    private InventoryJpaEntity inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private CustomerJpaEntity customer;
}
