package ru.gb.spring.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.wintermarket.entity.Order;
import ru.gb.spring.wintermarket.entity.OrderItem;
import ru.gb.spring.wintermarket.entity.Product;
import ru.gb.spring.wintermarket.entity.User;
import ru.gb.spring.wintermarket.model.Cart;
import ru.gb.spring.wintermarket.model.CartItem;
import ru.gb.spring.wintermarket.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(User user){
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        List<OrderItem> orderItemList = new LinkedList<>();

        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());

        for(CartItem cartItem : cart.getItems()){
                Optional<Product> product = productService.findById(cartItem.getProductId());

                OrderItem orderItem = new OrderItem(
                        null,
                        product.get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice() );

                orderItemList.add(orderItem);

        }

        order.setItems(orderItemList);
        orderRepository.save(order);
        cart.clear();
    }





}
