package com.toy.sakila.payment.application.port.in;

import com.toy.sakila.payment.domain.Payment;

public interface PaymentCreationUseCase {
    Payment create(PaymentCreationCommand command);
}
