package ru.gb.spring.wintermarket.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.spring.wintermarket.entity.Product;

//Спецификация для поиска: больше заданного/меньше/часть тайтла
public class ProductSpecification {

    public static Specification<Product> priceGreaterOrEqualsThan(int price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price);
    }

    public static Specification<Product> priceLessOrEqualsThan(int price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price);
    }

    public static Specification<Product> nameLike(String titlePart){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"),String.format("%%%s%%",titlePart));
    }






}
