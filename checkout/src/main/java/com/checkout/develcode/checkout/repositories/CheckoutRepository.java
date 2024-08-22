package com.checkout.develcode.checkout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.checkout.develcode.checkout.Checkout;


public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
