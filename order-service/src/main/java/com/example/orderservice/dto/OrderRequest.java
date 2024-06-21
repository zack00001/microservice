package com.example.orderservice.dto;

import com.example.orderservice.model.OrderLineItem;
import java.util.List;
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
public class OrderRequest {
    private List<OrderLineItemDto> orderLineItemDtoList;
}
