package com.ecommerce.microservices.product.service;

import com.ecommerce.microservices.product.model.Product;
import com.ecommerce.microservices.product.repository.ProductRepository;
import com.ecommerce.microservices.product.web.dto.ProductRequest;
import com.ecommerce.microservices.product.web.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created successfully: {}", product);

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}