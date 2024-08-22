package com.checkout.develcode.checkout.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.checkout.develcode.checkout.Checkout;
import com.checkout.develcode.checkout.dtos.CreateCheckoutDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/checkouts")
@Tag(name = "Checkout")
public interface CheckoutApi {

    @PostMapping
    public ResponseEntity<Checkout> create(@RequestBody(description = "Create a new checkout",
            required = true,
            content = @Content(schema = @Schema(
                    implementation = CreateCheckoutDto.class))) @org.springframework.web.bind.annotation.RequestBody @Valid CreateCheckoutDto createCheckout,
            UriComponentsBuilder uriComponentsBuilder);

    @GetMapping("{id}")
    public ResponseEntity<Checkout> findById(@PathVariable(required = true, name = "id") Long id);

    @GetMapping
    public Page<Checkout> list(Pageable pageable);


}
