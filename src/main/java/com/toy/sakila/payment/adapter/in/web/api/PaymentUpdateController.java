package com.toy.sakila.payment.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.payment.application.port.in.PaymentUpdateCommand;
import com.toy.sakila.payment.application.port.in.PaymentUpdateUseCase;
import com.toy.sakila.payment.domain.Payment;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.domain.Staff;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentUpdateController {

    private final PaymentUpdateUseCase staffUpdateUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> staffUpdate(
            @PathVariable Long id,
            @RequestBody PaymentUpdateCommand command
    ){
        Payment domain = staffUpdateUseCase.update(Payment.PaymentId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Payment 수정을 완료했습니다.")
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
            LocalDateTime createDate
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
                    .createDate(domain.getCreateDate())
                    .build();
        }
    }
}