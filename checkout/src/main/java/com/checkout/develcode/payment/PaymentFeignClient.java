package com.checkout.develcode.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.checkout.develcode.payment.dtos.PaymentValidationResponseDto;
import com.checkout.develcode.payment.dtos.RequestPaymentValidateDto;

@FeignClient(name = "external-payment-service", url = "http://payment_service:3000/payment")
public interface PaymentFeignClient {

    @PostMapping("/validate")
    PaymentValidationResponseDto validate(RequestPaymentValidateDto requestData);

}
