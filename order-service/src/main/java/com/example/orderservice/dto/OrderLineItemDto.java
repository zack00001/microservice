package com.example.orderservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: order-service
 * @description:
 * @author: zhangjiajun
 * @create: 2024/06/07
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderLineItemDto {
    private Long id;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
