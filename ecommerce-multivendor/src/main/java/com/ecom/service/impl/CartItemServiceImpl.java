package com.ecom.service.impl;

import com.ecom.model.CartItems;
import com.ecom.model.User;
import com.ecom.repository.CartItemRepository;
import com.ecom.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private  final CartItemRepository cartItemRepository;

    @Override
    public CartItems updateCartItem(Long userId, Long id, CartItems cartItems) throws Exception {
        CartItems item = findCartItemById(id);

        User cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItems.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()* item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        else{
            throw new Exception("you can't update this cart item");
        }
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItems item = findCartItemById(cartItemId);
        User cartItemUser = item.getCart().getUser();

        if(cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }
        else {
            throw new Exception("you cant delete this item");
        }
    }

    @Override
    public CartItems findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(()-> new Exception("cart item not found with id "+id));
    }
}
