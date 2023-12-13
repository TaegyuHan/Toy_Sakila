package com.toy.sakila.staff.adapter.out.persistence;


import com.toy.sakila.address.adapter.out.persistence.AddressJpaEntity;
import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.store.adapter.out.persistence.StoreJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff")
public class StaffJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte staffId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", insertable = false, updatable = false)
    private AddressJpaEntity addressId;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", insertable = false, updatable = false)
    private StoreJpaEntity store;

    @Lob
    @Column(name = "picture")
    private Blob picture;

    @Column(name = "email", length = 50)
    private String email;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", columnDefinition = "CHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
    private String password;
}