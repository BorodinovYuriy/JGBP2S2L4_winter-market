package ru.gb.spring.wintermarket.validators;

import org.springframework.stereotype.Component;
import ru.gb.spring.wintermarket.dto.ProductDto;
import ru.gb.spring.wintermarket.exceptions.ValidationException;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if(productDto.getPrice() < 0){
            errors.add("Цена продукта не может быть отрицательной");
        }
        if(productDto.getTitle().isBlank()){
            errors.add("Продукт должен иметь название");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}

