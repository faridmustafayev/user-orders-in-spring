package com.example.user.orders.dao.repository;

import com.example.user.orders.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Override
    List<OrderEntity> findAll();
}
