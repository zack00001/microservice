package com.example.orderservice.controller;

import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @program: order-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/07
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    /**
     * circuit breaker status turns on in certain situations by configuration
     * like fifty percent of requests failing during sliding window, then turns on 
     * @param orderRequest
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "place order successfully";
    }

    /**
     * execute fallback method when throwing runtimeException
     * @param orderRequest
     * @param runtimeException
     * @return
     */
    public String fallback(OrderRequest orderRequest, RuntimeException runtimeException) {
        return "This is fallback message";
    }
}
