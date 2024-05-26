package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.CartItem;
import com.example.DiamondShopSystem.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart_items")
@CrossOrigin(origins = "http://localhost:3000")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.findAllCartItems();
    }

    @PostMapping
    public void createCartItem(@RequestBody CartItem cartItem) {
        cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping
    public void deleteCartItem(@RequestBody CartItem cartItem) {
        cartItemService.deleteCartItem(cartItem);
    }
}
