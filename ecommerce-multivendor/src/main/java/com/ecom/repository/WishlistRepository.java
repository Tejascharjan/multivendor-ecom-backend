package com.ecom.repository;

import com.ecom.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList, Long> {

    WishList findByUserId(Long userId);
}
