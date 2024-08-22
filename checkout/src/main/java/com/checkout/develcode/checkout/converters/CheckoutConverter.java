package com.checkout.develcode.checkout.converters;

import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;

public class CheckoutConverter {


    public static final Checkout createCheckoutDtoToCheckout(CreateCheckoutDto createCheckoutDto) {
        var checkout = new Checkout();
        checkout.setEmail(createCheckoutDto.email());
        checkout.setAmount(createCheckoutDto.amount());
        return checkout;
    }
}
