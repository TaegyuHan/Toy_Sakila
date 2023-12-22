package com.toy.sakila.payment.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.payment.application.port.out.PaymentReadPort;
import com.toy.sakila.payment.application.port.out.PaymentSavePort;
import com.toy.sakila.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
@PersistenceAdapter
public class PaymentPersistenceAdapter implements PaymentReadPort, PaymentSavePort {

    private final SpringDataPaymentRepository springDataPaymentRepository;
    private final PaymentPersistenceMapper mapper;

    @Override
    public Payment save(Payment domain) {
        return Optional.of(domain)
                .map(mapper::toJpaEntity)
                .map(springDataPaymentRepository::save)
                .map(mapper::toDomainEntity)
                .orElseThrow();
    }

    @Override
    public Payment findById(Payment.PaymentId id) {
        return springDataPaymentRepository.findById(id.getValue())
                .map(mapper::toDomainEntity)
                .orElseThrow();
    }
}