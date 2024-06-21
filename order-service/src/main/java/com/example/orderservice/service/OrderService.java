package com.example.orderservice.service;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderLineItemDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.event.OrderPlacedEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItem;
import com.example.orderservice.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @program: order-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/07
 **/
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WebClient.Builder webClientBuilder;
    private final InventoryClient inventoryClient;
    
    public void placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).orderLineItemList(
                orderRequest.getOrderLineItemDtoList().stream().map(this::toOrderLineItem).collect(
                        Collectors.toList())).build();
        List<String> skuCodeList = order.getOrderLineItemList().stream().map(
                OrderLineItem::getSkuCode).collect(
                Collectors.toList());

//        InventoryResponse[] inventoryResponseArr = webClientBuilder.build().get()
//                .uri("http://inventory-service/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodeList).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class).block();

        List<InventoryResponse> list = inventoryClient.isInStock(skuCodeList);
        
//        boolean allProductsInStock = Arrays.stream(inventoryResponseArr).allMatch(InventoryResponse::isInStock);
        boolean allProductsInStock = list.stream().allMatch(InventoryResponse::isInStock);
        if (allProductsInStock) {
            orderRepository.save(order);
//            kafkaTemplate.send("notificationTopic",
//                    OrderPlacedEvent.builder().orderNumber(order.getOrderNumber()).build());

            kafkaTemplate.send("notificationTopicString",
                    order.getOrderNumber());
        }else {
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItem toOrderLineItem(OrderLineItemDto item) {
        return OrderLineItem.builder().id(item.getId()).skuCode(item.getSkuCode())
                .price(item.getPrice()).quantity(item.getQuantity()).build();
    }
}
