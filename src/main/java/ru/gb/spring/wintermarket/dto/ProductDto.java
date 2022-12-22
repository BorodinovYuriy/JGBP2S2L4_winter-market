package ru.gb.spring.wintermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//для джэксона
@AllArgsConstructor
public class ProductDto {
private Long id;
private String title;
private int price;
}

