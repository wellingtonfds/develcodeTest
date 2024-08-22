package com.checkout.develcode.checkout.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.converters.CheckoutConverter;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;
import com.checkout.develcode.checkout.repositories.CheckoutRepository;


@Service
public class CheckoutService {

    private CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Transactional
    public Checkout create(CreateCheckoutDto createCheckout) {
        var checkout = CheckoutConverter.createCheckoutDtoToCheckout(createCheckout);
        checkoutRepository.save(checkout);
        return checkout;
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
