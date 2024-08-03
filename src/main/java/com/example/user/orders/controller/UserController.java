package com.example.user.orders.controller;

import com.example.user.orders.model.request.CreateOrUpdateOrderRequest;
import com.example.user.orders.model.request.CreateUserRequest;
import com.example.user.orders.model.response.UserResponse;
import com.example.user.orders.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody CreateUserRequest request){
        userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable Long id,
                           CreateUserRequest request){
        userService.updateUser(id, request);
    }

    @PostMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void addUserOrder(@PathVariable Long id,
                             @RequestBody CreateOrUpdateOrderRequest request){
        userService.addUserOrder(id, request);
    }

}