package com.ecom.controller;

import com.ecom.exceptions.ProductException;
import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.request.AddItemRequest;
import com.ecom.response.ApiResponse;
import com.ecom.service.CartItemService;
import com.ecom.service.CartService;
import com.ecom.service.ProductService;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItems> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization") String jwt) throws ProductException,Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItems items = cartService.addCartItem(user,product,req.getSize(),req.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added to Cart Successfully");
        return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Remove from Cart");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItems> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItems cartItems,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        CartItems updatedCartItem = null;
        if (cartItems.getQuantity() > 0){
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId,cartItems);
        }
        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
    }
}
