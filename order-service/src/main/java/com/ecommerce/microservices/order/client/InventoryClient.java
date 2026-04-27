package com.ecommerce.microservices.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory")
    boolean checkInventory(@RequestParam("sku") String sku, @RequestParam("quantity") Integer quantity);
}
