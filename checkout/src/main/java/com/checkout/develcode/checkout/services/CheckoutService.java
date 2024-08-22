package com.checkout.develcode.checkout.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.CheckoutStatusEnum;
import com.checkout.develcode.checkout.converters.CheckoutConverter;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;
import com.checkout.develcode.checkout.repositories.CheckoutRepository;
import com.checkout.develcode.expections.CustomFeignException;
import com.checkout.develcode.payment.PaymentService;
import com.checkout.develcode.payment.dtos.RequestPaymentValidateDto;


@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final PaymentService paymentService;

    public CheckoutService(CheckoutRepository checkoutRepository, PaymentService paymentService) {
        this.checkoutRepository = checkoutRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public Checkout create(CreateCheckoutDto createCheckout) {


        var checkout = CheckoutConverter.createCheckoutDtoToCheckout(createCheckout);
        checkoutRepository.save(checkout);
        var requestPaymentValidateDto = new RequestPaymentValidateDto(checkout.getId(),
                checkout.getAmount(), checkout.getEmail());
        try {
            paymentService.validate(requestPaymentValidateDto);
            checkout.setStatusEnum(CheckoutStatusEnum.COMPLETED);
            checkoutRepository.save(checkout);
            return checkout;
        } catch (CustomFeignException ex) {
            checkout.setStatusEnum(CheckoutStatusEnum.CANCELLED);
            checkoutRepository.save(checkout);
            return checkout;

        }
    }


    @Transactional(readOnly = true)
    public Checkout findById(Long id) {
        return checkoutRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<Checkout> list(Pageable pageable) {
        return checkoutRepository.findAll(pageable);
    }
}
