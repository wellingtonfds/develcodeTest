package com.checkout.develcode.checkout.dtos;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCheckoutDto(@NotEmpty String email, @NotNull BigDecimal amount) {

}
