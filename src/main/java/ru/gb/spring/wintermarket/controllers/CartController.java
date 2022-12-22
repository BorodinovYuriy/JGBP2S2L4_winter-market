package ru.gb.spring.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.wintermarket.model.Cart;
import ru.gb.spring.wintermarket.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;


    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.add(id);
    }
    @GetMapping()
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @PutMapping("/increase/{id}")
    public void increaseProductInCart(@PathVariable Long id){
        cartService.increaseProductInCart(id);
    }
    @PutMapping("/decrease/{id}")
    public void decreaseProductInCart(@PathVariable Long id){
        cartService.decreaseProductInCart(id);
    }
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        cartService.removeProductById(id);

    }
    @DeleteMapping()
    public void clearCart(){
        cartService.clear();

    }








}

