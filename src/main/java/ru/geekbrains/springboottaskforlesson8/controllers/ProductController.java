package ru.geekbrains.springboottaskforlesson8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboottaskforlesson8.models.Product;
import ru.geekbrains.springboottaskforlesson8.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    public final ProductService service;

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_cost", defaultValue = "0") Double minCost,
            @RequestParam(name = "max_cost", required = false) Double maxCost
    ) {
        if (maxCost == null) {
            maxCost = Double.MAX_VALUE;
        }
        return service.findAllByCost(minCost, maxCost);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return service.findProductById(id).get();
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return service.saveOrUpdate(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }
}
