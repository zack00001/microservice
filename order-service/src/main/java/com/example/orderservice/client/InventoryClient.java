package com.example.orderservice.client;

import com.example.orderservice.dto.InventoryResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam List<String> skuCodeList);
}
