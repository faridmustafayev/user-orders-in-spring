package com.example.user.orders.service.abstraction;

import com.example.user.orders.dao.entity.UserEntity;
import com.example.user.orders.model.criteria.PageCriteria;
import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    void createOrder(CreateOrUpdateOrderRequest request, UserEntity user);

    OrderResponse getOrder(Long orderId);

    List<OrderResponse> getOrders(PageCriteria pageCriteria);

    void updateOrder(Long orderId, CreateOrUpdateOrderRequest request);

    void deleteOrder(Long orderId);
}
