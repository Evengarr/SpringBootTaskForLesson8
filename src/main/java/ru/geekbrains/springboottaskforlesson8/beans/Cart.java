package ru.geekbrains.springboottaskforlesson8.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.springboottaskforlesson8.exception.ResourceNotFoundException;
import ru.geekbrains.springboottaskforlesson8.models.entities.OrderItem;
import ru.geekbrains.springboottaskforlesson8.models.entities.Product;
import ru.geekbrains.springboottaskforlesson8.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private double totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o: items) {
            if(o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculated();
                return;
            }
        }

        Product p = productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Не удалось найти продукт под ID " + id + " (добавлен в корзину)" ));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculated();
    }

    public void clear(){
        items.clear();
        recalculated();
    }

    public void recalculated() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }
}
