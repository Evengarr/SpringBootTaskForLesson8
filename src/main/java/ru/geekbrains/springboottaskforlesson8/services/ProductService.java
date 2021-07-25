package ru.geekbrains.springboottaskforlesson8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboottaskforlesson8.models.dtos.ProductDto;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import ru.geekbrains.springboottaskforlesson8.repositorues.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Optional<ProductDto> findProductDtoById(Long id) {
        return repository.findById(id).map(ProductDto::new);
    }

    public Optional<Product> findProductById(Long id) {
        return repository.findById(id).map(Product::new);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        if (page < 0) throw new RuntimeException();
        return repository.findAll(spec, PageRequest.of(page-1, pageSize)).map(ProductDto::new);
    }

    public Product saveOrUpdate(Product product) {
        return repository.save(product);
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}