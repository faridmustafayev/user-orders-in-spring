package com.example.user.orders.mapper;

import com.example.user.orders.dao.entity.OrderEntity;
import com.example.user.orders.dao.entity.UserEntity;
import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.response.OrderResponse;

import static com.example.user.orders.model.enums.UserAndOrderStatus.*;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(CreateOrUpdateOrderRequest request, UserEntity user) {
        return OrderEntity.builder()
                .user(user)
                .totalAmount(request.getTotalAmount())
                .status(DRAFT)
                .build();
    }

    public OrderResponse buildOrderResponse(OrderEntity order) {
        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
