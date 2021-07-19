package ru.geekbrains.springboottaskforlesson8.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import ru.geekbrains.springboottaskforlesson8.services.CartService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    public final CartService service;

    //показать содержимое корзины
    @GetMapping
    public List<Product> findCart(){
        return service.findCart();
    }

    //добавление продукта в корзину
    @PostMapping
    public List<Product> addProductInCart(@RequestBody Product product, Integer quantity) {
        service.addProductInCart(product, quantity);
        return service.findCart();
    }

    //удаление продукта по названию
    @GetMapping("/{title}")
    public void deleteProductInCart(@PathVariable Product product) {
        service.deleteProductInCart(product);
    }

    //очистка корзины
    @DeleteMapping("/delete")
    public void clearCart(){
        service.clearCart();
    }

}
