package com.ecom.service;

import com.ecom.model.CartItems;

public interface CartItemService {

    CartItems updateCartItem(Long userId, Long id, CartItems cartItems) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItems findCartItemById(Long id) throws Exception;
}
