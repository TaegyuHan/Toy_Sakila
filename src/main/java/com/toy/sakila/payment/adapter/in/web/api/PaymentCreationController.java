package com.toy.sakila.payment.adapter.in.web.api;


import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.payment.application.port.in.PaymentCreationCommand;
import com.toy.sakila.payment.application.port.in.PaymentCreationUseCase;
import com.toy.sakila.payment.domain.Payment;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.domain.Staff;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentCreationController {

    private final PaymentCreationUseCase paymentCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> paymentCreation(
            @RequestBody PaymentCreationCommand command
    ){
        Payment domain = paymentCreationUseCase.create(command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(200)
                .data(OutputDTO.of(domain))
                .message("Payment 생성을 완료했습니다.")
                .build();

        return ResponseEntity.ok(body);
    }

    @Builder
    private record OutputDTO(
            Long paymentId,
            Customer customer,
            Staff staff,
            Rental rental,
            BigDecimal amount,
            LocalDateTime paymentDate,
            LocalDateTime lastUpdate,
            LocalDateTime createdDate
    ){
        private static OutputDTO of(Payment domain) {
            return OutputDTO.builder()
                    .paymentId(domain.getId().getValue())
                    .customer(domain.getCustomer())
                    .staff(domain.getStaff())
                    .rental(domain.getRental())
                    .amount(domain.getAmount())
                    .paymentDate(domain.getPaymentDate())
                    .lastUpdate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}