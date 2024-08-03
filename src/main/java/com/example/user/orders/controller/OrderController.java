package com.example.user.orders.controller;

import com.example.user.orders.model.criteria.PageCriteria;
import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.response.OrderResponse;
import com.example.user.orders.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping
    public List<OrderResponse> getOrders(PageCriteria pageCriteria){
        return orderService.getOrders(pageCriteria);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateOrder(@PathVariable Long id,
                            CreateOrUpdateOrderRequest request){
        orderService.updateOrder(id, request);
    }


    @DeleteMapping("/{orderId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }

}