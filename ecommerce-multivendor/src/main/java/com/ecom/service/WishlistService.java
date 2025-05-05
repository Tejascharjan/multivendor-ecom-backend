package com.ecom.service;

import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.model.WishList;

public interface WishlistService {
    WishList createWishlist(User user);
    WishList getWishlistByUserId(User user);
    WishList addProductToWishlist(User user, Product product);
}
