package com.example.user.orders.model.response;

import com.example.user.orders.model.enums.UserAndOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private UserAndOrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
}
