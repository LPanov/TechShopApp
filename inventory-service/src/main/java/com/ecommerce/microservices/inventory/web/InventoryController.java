package com.ecommerce.microservices.inventory.web;

import com.ecommerce.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isAvailable(@RequestParam String sku, @RequestParam Integer quantity) {
        return inventoryService.isAvailable(sku, quantity);
    }
}
