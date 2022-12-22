package ru.gb.spring.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.wintermarket.entity.User;
import ru.gb.spring.wintermarket.services.OrderService;
import ru.gb.spring.wintermarket.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){
        User user = userService.
                findByUsername(principal.getName())
                .orElseThrow(RuntimeException::new);
        orderService.createOrder(user);
    }

}
