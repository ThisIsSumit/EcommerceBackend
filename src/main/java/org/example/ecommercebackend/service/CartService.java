package org.example.ecommercebackend.service;

import org.example.ecommercebackend.payload.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
