package ru.gb.spring.wintermarket.model;

import lombok.Data;
import ru.gb.spring.wintermarket.dto.ProductDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    public void  add(ProductDto productDto) {
            for(CartItem p : items){
                if(p.getProductId().equals(productDto.getId())){
                    p.setQuantity(p.getQuantity() + 1);
                    recalculate();
                    return;
                }
            }
        items.add(new CartItem(
                productDto.getId(),
                productDto.getTitle(),
                1,
                productDto.getPrice())
        );
        recalculate();
    }
    private void recalculate(){
        totalPrice = 0;
        for(CartItem item : items){
            item.setPrice(item.getPricePerProduct() * item.getQuantity());
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }
    public void increaseProduct(ProductDto productDto) {
        for(CartItem p : items){
            if(p.getProductId().equals(productDto.getId())) {
                p.setQuantity(p.getQuantity() + 1);
                recalculate();
                return;
            }
        }
    }
    public void decreaseProduct(ProductDto productDto) {
        for(CartItem p : items){
            if(p.getProductId().equals(productDto.getId())) {
                if(p.getQuantity() > 1){
                    p.setQuantity(p.getQuantity() - 1);
                    recalculate();
                    return;
                }
            }
        }
    }
    public void remove(ProductDto productDto) {
        if( items.removeIf(p -> p.getProductId().
                            equals(productDto.getId())) ){
            recalculate();
        }
    }
    public void clear() {
        items.clear();
        totalPrice = 0;
    }
}

