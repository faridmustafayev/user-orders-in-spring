package com.example.user.orders.mapper;

import com.example.user.orders.dao.entity.UserEntity;
import com.example.user.orders.model.request.CreateUserRequest;
import com.example.user.orders.model.response.OrderResponse;
import com.example.user.orders.model.response.UserResponse;

import static com.example.user.orders.model.enums.UserAndOrderStatus.*;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .salary(request.getSalary())
                .status(DRAFT)
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .build();
    }

    public UserResponse buildUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .salary(user.getSalary())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .orders(user.getOrders().stream()
                        .map(orderEntity -> OrderResponse.builder()
                                .id(orderEntity.getId())
                                .status(orderEntity.getStatus())
                                .totalAmount(orderEntity.getTotalAmount())
                                .createdAt(orderEntity.getCreatedAt())
                                .build())
                        .toList())
                .build();
    }
}
