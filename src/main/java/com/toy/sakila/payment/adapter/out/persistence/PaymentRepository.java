package com.toy.sakila.payment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, Long> {
}
