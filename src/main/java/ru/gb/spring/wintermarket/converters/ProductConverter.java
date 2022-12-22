package ru.gb.spring.wintermarket.converters;

import org.springframework.stereotype.Component;
import ru.gb.spring.wintermarket.dto.ProductDto;
import ru.gb.spring.wintermarket.entity.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getPrice()
        );
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice()
        );
    }
}

