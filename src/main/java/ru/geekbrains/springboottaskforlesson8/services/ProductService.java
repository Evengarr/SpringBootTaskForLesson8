package ru.geekbrains.springboottaskforlesson8.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboottaskforlesson8.models.Product;
import ru.geekbrains.springboottaskforlesson8.repositorues.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Optional<Product> findProductById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllByCost(double min, double max) {
        return repository.findAllByCostBetween(min, max);
    }

    public Product saveOrUpdate(Product product) {
        return repository.save(product);
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}