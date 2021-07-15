package ru.geekbrains.springboottaskforlesson8.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double cost;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
    }
}
