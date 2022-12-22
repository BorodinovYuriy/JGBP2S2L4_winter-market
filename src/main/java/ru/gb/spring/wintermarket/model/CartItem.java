package ru.gb.spring.wintermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    public String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public CartItem(Long productId, String productTitle, int quantity, int pricePerProduct) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        price = pricePerProduct * quantity;
    }
    public void changeQuantity(int delta) {
        quantity += delta;
        price = pricePerProduct * quantity;
    }

}

