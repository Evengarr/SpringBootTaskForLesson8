package ru.geekbrains.springboottaskforlesson8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboottaskforlesson8.exception.ResourceNotFoundException;
import ru.geekbrains.springboottaskforlesson8.models.dtos.ProductDto;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import ru.geekbrains.springboottaskforlesson8.repositorues.specifications.ProductSpecification;
import ru.geekbrains.springboottaskforlesson8.services.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    public final ProductService service;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return service.findAll(ProductSpecification.build(params), page, 10);
    }

    @GetMapping("/{id}")
    public ProductDto findProductDtoById(@PathVariable Long id) {
        return service.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с Id " + id + " не обнаружен"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return service.saveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return service.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        service.deleteProductById(id);
    }
}
