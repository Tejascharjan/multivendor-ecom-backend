package com.ecom.service;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Product;
import com.ecom.model.User;

public interface CartService {

    public CartItems addCartItem(User user, Product product, String size, int quantity);
    public Cart findUserCart(User user);
}
