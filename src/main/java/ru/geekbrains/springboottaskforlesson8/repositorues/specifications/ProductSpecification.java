package ru.geekbrains.springboottaskforlesson8.repositorues.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import org.springframework.util.MultiValueMap;

public class ProductSpecification {
    private static Specification<Product> priceGreaterOrEqualsThan(double minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }

    private static Specification<Product> priceLessOrEqualsThan(double maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

    private static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_cost") && !params.getFirst("min_cost").isBlank()) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(Double.parseDouble(params.getFirst("min_cost"))));
        }
        if (params.containsKey("max_cost") && ! params.getFirst("max_cost").isBlank()) {
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(Double.parseDouble(params.getFirst("max_cost"))));
        }
        if (params.containsKey("title") && ! params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
