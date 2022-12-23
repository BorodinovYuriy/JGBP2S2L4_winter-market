package ru.gb.spring.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.wintermarket.entity.OrderItem;
import ru.gb.spring.wintermarket.repositories.OrderItemRepository;
import ru.gb.spring.wintermarket.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    OrderItemRepository orderItemRepository;

}
