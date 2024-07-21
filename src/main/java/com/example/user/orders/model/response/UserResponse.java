package com.example.user.orders.model.response;

import com.example.user.orders.model.enums.UserAndOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate dateOfBirth;
    private String email;
    private Date createdAt;
    private UserAndOrderStatus status;
    private List<OrderResponse> orders = new ArrayList<>();
}
