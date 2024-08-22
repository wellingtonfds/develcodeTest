package com.checkout.develcode.checkout.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.CheckoutStatusEnum;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;
import com.checkout.develcode.checkout.services.CheckoutService;

import jakarta.validation.Valid;

@RestController
public class CheckoutController implements CheckoutApi {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Override
    public ResponseEntity<Checkout> create(@Valid CreateCheckoutDto createCheckout,
            UriComponentsBuilder uriComponentsBuilder) {

        var checkout = checkoutService.create(createCheckout);
        var uri = uriComponentsBuilder.path("/api/checkouts/{id}").buildAndExpand(checkout.getId())
                .toUri();
        if (checkout.getStatusEnum() != CheckoutStatusEnum.COMPLETED) {
            return ResponseEntity.badRequest().body(checkout);
        }
        return ResponseEntity.created(uri).body(checkout);
    }

    @Override
    public ResponseEntity<Checkout> findById(Long id) {
        var checkout = checkoutService.findById(id);
        return ResponseEntity.ok().body(checkout);

    }

    @Override
    public Page<Checkout> list(Pageable pageable) {
        return checkoutService.list(pageable);
    }

}
