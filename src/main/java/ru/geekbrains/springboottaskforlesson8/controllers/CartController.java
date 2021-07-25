package ru.geekbrains.springboottaskforlesson8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboottaskforlesson8.beans.Cart;
import ru.geekbrains.springboottaskforlesson8.models.dtos.CartDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clear();
    }
}
