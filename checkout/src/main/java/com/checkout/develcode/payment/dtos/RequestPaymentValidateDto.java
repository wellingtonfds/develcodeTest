package com.checkout.develcode.payment.dtos;

import java.math.BigDecimal;

public record RequestPaymentValidateDto(Long orderId, BigDecimal amount, String userEmail) {

}
