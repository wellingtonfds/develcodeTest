package com.checkout.develcode.payment;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.checkout.develcode.payment.dtos.PaymentValidationResponseDto;
import com.checkout.develcode.payment.dtos.RequestPaymentValidateDto;

@Service
public class PaymentService {


    private PaymentFeignClient paymentClient;

    public PaymentService(RestClient.Builder restClient, PaymentFeignClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    public PaymentValidationResponseDto validate(RequestPaymentValidateDto requestData) {

        var response = paymentClient.validate(requestData);

        return response;



    }
}
