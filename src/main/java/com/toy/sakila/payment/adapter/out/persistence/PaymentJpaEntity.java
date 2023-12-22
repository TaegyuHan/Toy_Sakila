package com.toy.sakila.payment.adapter.out.persistence;


import com.toy.sakila.common.adapter.out.persistence.BaseEntity;
import com.toy.sakila.customer.adapter.out.persistence.CustomerJpaEntity;
import com.toy.sakila.rental.adapter.out.persistence.RentalJpaEntity;
import com.toy.sakila.staff.adapter.out.persistence.StaffJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerJpaEntity customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffJpaEntity staff;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private RentalJpaEntity rental;

    @Column(name = "amount", nullable = false, precision = 5, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
}
