package ru.gb.spring.wintermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.wintermarket.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
