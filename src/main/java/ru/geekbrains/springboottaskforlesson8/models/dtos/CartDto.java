package ru.geekbrains.springboottaskforlesson8.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springboottaskforlesson8.beans.Cart;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CartDto {
    private List<OrderItemDto> items;
    private double totalPrice;

    public CartDto(Cart cart){
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.totalPrice = cart.getTotalPrice();
    }
}
