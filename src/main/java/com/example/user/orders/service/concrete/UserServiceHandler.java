package com.example.user.orders.service.concrete;

import com.example.user.orders.dao.entity.UserEntity;
import com.example.user.orders.dao.repository.UserRepository;
import com.example.user.orders.exception.NotFoundException;
import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.request.CreateUserRequest;
import com.example.user.orders.model.response.UserResponse;
import com.example.user.orders.service.abstraction.OrderService;
import com.example.user.orders.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.user.orders.mapper.UserMapper.USER_MAPPER;
import static com.example.user.orders.model.enums.ExceptionConstants.USER_NOT_FOUND;
import static com.example.user.orders.model.enums.UserAndOrderStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    @Override
    public void createUser(CreateUserRequest request) {
        log.info("ActionLog.createUser.start creating user with request: {}", request);
        userRepository.save(USER_MAPPER.buildUserEntity(request));
    }

    @Override
    public UserResponse getUser(Long userId) {
        log.info("ActionLog.getUser.start with id: {}", userId);
        var user = fetchUserIfExist(userId);
        log.info("ActionLog.getUser.success with id: {}", userId);
        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("ActionLog.getUsers.start");
        var users = userRepository.findAll();
        log.info("ActionLog.getUsers.success");
        return users.stream()
                .map(USER_MAPPER::buildUserResponse)
                .toList();
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("ActionLog.deleteUser.start user with id: {}", userId);
        var user = fetchUserIfExist(userId);
        log.info("ActionLog.deleteUser.success user with id: {}", userId);
        user.setStatus(DELETED);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, CreateUserRequest request) {
        log.info("ActionLog.updateUser.start user with id: {}", userId);
        var user = fetchUserIfExist(userId);
        user.setStatus(IN_PROGRESS);

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getSalary() != null) {
            user.setSalary(request.getSalary());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }

        log.info("ActionLog.updateUser.start user with id: {}", userId);

        userRepository.save(user);
    }

    @Override
    public void addUserOrder(Long userId, CreateOrUpdateOrderRequest request) {
        log.info("ActionLog.addUserOrder.start adding order for user with id: {}", userId);
        var user = fetchUserIfExist(userId);
        orderService.createOrder(request, user);
        userRepository.save(user);
    }

    private UserEntity fetchUserIfExist(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new NotFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
                });
    }

}