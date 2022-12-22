package ru.gb.spring.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.wintermarket.converters.ProductConverter;
import ru.gb.spring.wintermarket.model.Cart;
import ru.gb.spring.wintermarket.entity.Product;
import ru.gb.spring.wintermarket.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }
    public Cart getCurrentCart() {
        return tempCart;// TODO: 15.12.2022 дальше метод будет модифицироваться
    }
    public void add(Long productId){
        Product product = productService.
                findById(productId).
                orElseThrow(()-> new ResourceNotFoundException
                        ("Невозможно добавить в корзину," +
                                " id не найден. id: " + productId));
        tempCart.add(productConverter.entityToDto(product));
    }

    public void increaseProductInCart(Long id) {
       Product product = productService.findById(id).
               orElseThrow(()->
                       new ResourceNotFoundException("Невозможно найти объект с id: "+ id));
        tempCart.increaseProduct(productConverter.entityToDto(product));
    }

    public void decreaseProductInCart(Long id) {
        Product product = productService.findById(id).
                orElseThrow(()->
                        new ResourceNotFoundException("Невозможно найти объект с id: "+ id));
        tempCart.decreaseProduct(productConverter.entityToDto(product));
    }

    public void removeProductById(Long id) {
        Product product = productService.findById(id).
                orElseThrow(()->
                        new ResourceNotFoundException("Невозможно найти объект с id: "+ id));
        tempCart.remove(productConverter.entityToDto(product));
    }

    public void clear() {
        tempCart.clear();
    }
}
