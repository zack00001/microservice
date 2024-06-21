package com.example.product_service.service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: product-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/07
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        productRepository.save(product);
        
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).build();
    }
}
