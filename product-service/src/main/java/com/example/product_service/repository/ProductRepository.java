package com.example.product_service.repository;

import com.example.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: product-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/07
 **/
public interface ProductRepository extends MongoRepository<Product, String> {
    
}
