package com.example.user.orders.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionConstants {
    ORDER_NOT_FOUND("ORDER_NOT_FOUND", "Order not found"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred");
    private String code;
    private String message;
}
