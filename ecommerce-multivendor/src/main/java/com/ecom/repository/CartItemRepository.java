package com.ecom.repository;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    CartItems findByCartAndProductAndSize(Cart cart, Product product, String size);
}
