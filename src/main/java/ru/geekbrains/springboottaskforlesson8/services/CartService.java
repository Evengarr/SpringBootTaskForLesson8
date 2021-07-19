package ru.geekbrains.springboottaskforlesson8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import ru.geekbrains.springboottaskforlesson8.repositorues.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Map<Product, Integer> cart = new HashMap<>();

    public List<Product> findCart() {
        Collection<Integer> c = cart.values();
        List<Product> list = new ArrayList<>();
        list.add((Product) c);
        return list;
    }

    public Map<Product, Integer> addProductInCart(Product product, Integer quantity) {
        cart.put(product, quantity);
        return cart;
    }

    public void deleteProductInCart(Product product) {
        cart.remove(product);
    }

    public void clearCart() {
        cart.clear();
    }
}
