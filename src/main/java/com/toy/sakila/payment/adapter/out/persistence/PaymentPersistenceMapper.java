package com.toy.sakila.payment.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.customer.adapter.out.persistence.CustomerPersistenceMapper;
import com.toy.sakila.payment.domain.Payment;
import com.toy.sakila.rental.adapter.out.persistence.RentalPersistenceMapper;
import com.toy.sakila.staff.adapter.out.persistence.StaffPersistenceMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Mapper
public class PaymentPersistenceMapper {

    private final CustomerPersistenceMapper customerPersistenceMapper;
    private final StaffPersistenceMapper staffPersistenceMapper;
    private final RentalPersistenceMapper rentalPersistenceMapper;

    public PaymentJpaEntity toJpaEntity(Payment domain) {
        return PaymentJpaEntity.builder()
                .paymentId(domain.getId().getValue())
                .customer(customerPersistenceMapper.mapToJpaEntity(domain.getCustomer()))
                .staff(staffPersistenceMapper.mapToJpaEntity(domain.getStaff()))
                .rental(rentalPersistenceMapper.mapToJpaEntity(domain.getRental()))
                .amount(domain.getAmount())
                .paymentDate(domain.getPaymentDate())
                .build();
    }

    public Payment toDomainEntity(PaymentJpaEntity entity) {
        return Payment.builder()
                .id(Payment.PaymentId.of(entity.getPaymentId()))
                .customer(customerPersistenceMapper.mapToDomainEntity(entity.getCustomer()))
                .staff(staffPersistenceMapper.mapToDomainEntity(entity.getStaff()))
                .rental(rentalPersistenceMapper.mapToDomainEntity(entity.getRental()))
                .amount(entity.getAmount())
                .paymentDate(entity.getPaymentDate())
                .build();
    }
}