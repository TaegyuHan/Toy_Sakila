package com.toy.sakila.customer.adapter.in.web.api;

import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.customer.application.port.in.CustomerUpdateCommand;
import com.toy.sakila.customer.application.port.in.CustomerUpdateUseCase;
import com.toy.sakila.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerUpdateController {

    private final CustomerUpdateUseCase customerUpdateUseCase;

    @PutMapping("/{id}")
    public void customerUpdate(
            @PathVariable String id,
            @RequestBody CustomerUpdateCommand command
    ){
        Customer domain = customerUpdateUseCase.update(command);



    }
}
