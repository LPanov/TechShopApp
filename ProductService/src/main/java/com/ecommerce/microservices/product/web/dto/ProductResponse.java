package com.ecommerce.microservices.product.web.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price
) {
}