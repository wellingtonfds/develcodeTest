package com.checkout.develcode.checkout.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.CheckoutStatusEnum;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;
import com.checkout.develcode.checkout.services.CheckoutService;


@ExtendWith(MockitoExtension.class)
public class CheckoutControllerTest {

    @Mock
    private CheckoutService service;

    @Test
    public void testCreate() throws Exception {
        var amount = new BigDecimal(100L);
        var email = "teste@gmail.com";
        var checkoutCreate = new CreateCheckoutDto(email, amount);
        var checkout = new Checkout(10l, email, amount, CheckoutStatusEnum.COMPLETED,
                LocalDateTime.now(), LocalDateTime.now());

        when(service.create(checkoutCreate)).thenReturn(checkout);
        var newCheckout = service.create(checkoutCreate);
        assertNotNull(newCheckout);
    }
}
