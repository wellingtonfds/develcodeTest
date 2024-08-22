package com.checkout.develcode.payment.dtos;

public record PaymentValidationResponseDto(String id, String orderId, String amount, String status,
        String createdAt, String updatedAt) {

}
