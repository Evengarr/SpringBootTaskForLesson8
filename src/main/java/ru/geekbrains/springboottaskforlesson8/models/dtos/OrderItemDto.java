package ru.geekbrains.springboottaskforlesson8.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboottaskforlesson8.models.entities.OrderItem;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private double pricePerProduct;
    private double price;

    public OrderItemDto(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
