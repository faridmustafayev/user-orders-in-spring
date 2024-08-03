package com.example.user.orders.service.abstraction;

import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.request.CreateUserRequest;
import com.example.user.orders.model.response.UserResponse;

import java.util.List;

public interface UserService {
    void createUser(CreateUserRequest request);

    UserResponse getUser(Long userId);

    List<UserResponse> getUsers();

    void deleteUser(Long userId);

    void updateUser(Long userId, CreateUserRequest request);

    void addUserOrder(Long userId, CreateOrUpdateOrderRequest request);
}
