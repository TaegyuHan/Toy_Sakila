package com.toy.sakila.store.adapter.out.persistence;

import com.toy.sakila.address.adapter.out.persistence.AddressJpaEntity;
import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.staff.adapter.out.persistence.StaffJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store")
public class StoreJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", insertable = false, updatable = false)
    private StaffJpaEntity managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", insertable = false, updatable = false)
    private AddressJpaEntity address;
}