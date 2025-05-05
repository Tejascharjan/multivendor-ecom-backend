package com.ecom.service.impl;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.CartItemRepository;
import com.ecom.repository.CartRepository;
import com.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);
        CartItems isPresent = cartItemRepository.findByCartAndProductAndSize(cart,product,size);

        if(isPresent == null){
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setQuantity(quantity);
            cartItems.setUserId(user.getId());
            cartItems.setSize(size);
            cartItems.setCart(cart);

            int totalPrice = quantity * product.getSellingPrice();
            cartItems.setSellingPrice(totalPrice);
            cartItems.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItems);
            cartItems.setCart(cart);
            return cartItemRepository.save(cartItems);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItems cartItems : cart.getCartItems())
        {
            totalPrice += cartItems.getMrpPrice();
            totalDiscountedPrice += cartItems.getSellingPrice();
            totalItem += cartItems.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice <=0){
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount/mrpPrice)*100;
        return (int)discountPercentage;
    }
}
