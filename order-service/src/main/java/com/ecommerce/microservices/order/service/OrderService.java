package com.ecommerce.microservices.order.service;

import com.ecommerce.microservices.order.client.InventoryClient;
import com.ecommerce.microservices.order.dto.OrderRequest;
import com.ecommerce.microservices.order.model.Order;
import com.ecommerce.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.checkInventory(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = buildOrder(orderRequest);
            orderRepository.save(order);

            log.info("Order placed successfully. Order number: {}", order.getOrderNumber());
        } else {
            throw new RuntimeException("Product with sku code:" + orderRequest.skuCode() + " is out of stock");
        }

    }

    private static Order buildOrder(OrderRequest orderRequest) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
    }
}
