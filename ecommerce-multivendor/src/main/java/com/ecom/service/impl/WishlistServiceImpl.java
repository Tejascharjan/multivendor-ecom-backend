package com.ecom.service.impl;

import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.model.WishList;
import com.ecom.repository.WishlistRepository;
import com.ecom.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public WishList createWishlist(User user) {
        WishList wishList = new WishList();
        wishList.setUser(user);
        return wishlistRepository.save(wishList);
    }

    @Override
    public WishList getWishlistByUserId(User user) {
        WishList  wishList = wishlistRepository.findByUserId(user.getId());
        if(wishList==null){
            wishList = createWishlist(user);
        }
        return wishList;
    }

    @Override
    public WishList addProductToWishlist(User user, Product product) {
        WishList wishList = getWishlistByUserId(user);

        if (wishList.getProducts().contains(product)){
            wishList.getProducts().remove(product);
        }
        else{
            wishList.getProducts().add(product);
        }
        return wishlistRepository.save(wishList);
    }
}
