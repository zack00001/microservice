package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: inventory-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/11
 **/
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodeList);
}
