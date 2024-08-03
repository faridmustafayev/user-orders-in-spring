package com.example.user.orders.service.concrete;

import com.example.user.orders.dao.entity.OrderEntity;
import com.example.user.orders.dao.entity.UserEntity;
import com.example.user.orders.dao.repository.OrderRepository;
import com.example.user.orders.exception.NotFoundException;
import com.example.user.orders.model.criteria.PageCriteria;
import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.response.OrderResponse;
import com.example.user.orders.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.user.orders.mapper.OrderMapper.ORDER_MAPPER;
import static com.example.user.orders.model.enums.ExceptionConstants.ORDER_NOT_FOUND;
import static com.example.user.orders.model.enums.UserAndOrderStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(CreateOrUpdateOrderRequest request, UserEntity user) {
        log.info("ActionLog.createOrder.start creating user with request: {}", request);
        orderRepository.save(ORDER_MAPPER.buildOrderEntity(request, user));
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        log.info("ActionLog.getOrder.start order with id: {}", orderId);
        OrderEntity order = fetchOrderIfExist(orderId);
        log.info("ActionLog.getOrder.success order with id: {}", orderId);
        return ORDER_MAPPER.buildOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getOrders(PageCriteria pageCriteria) {
        log.info("ActionLog.getOrders.start");
        var orders = orderRepository.findAll(
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending())
        );

        log.info("ActionLog.getOrders.success");
        return orders.stream()
                .map(ORDER_MAPPER::buildOrderResponse)
                .toList();
    }

    @Override
    public void updateOrder(Long orderId, CreateOrUpdateOrderRequest request) {
        log.info("ActionLog.updateOrder.start with id: {}", orderId);
        var order = fetchOrderIfExist(orderId);
        order.setTotalAmount(request.getTotalAmount());
        log.info("ActionLog.updateOrder.success with id: {}", orderId);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        log.info("ActionLog.deleteOrder.start with id: {}", orderId);
        var order = fetchOrderIfExist(orderId);
        order.setStatus(DELETED);
        log.info("ActionLog.deleteOrder.success with id: {}", orderId);
        orderRepository.save(order);
    }

    private OrderEntity fetchOrderIfExist(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> {
                    return new NotFoundException(ORDER_NOT_FOUND.getCode(), ORDER_NOT_FOUND.getMessage());
                });
    }

}