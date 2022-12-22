package ru.gb.spring.wintermarket.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.spring.wintermarket.dto.ProductDto;
import ru.gb.spring.wintermarket.entity.Product;
import ru.gb.spring.wintermarket.exceptions.ResourceNotFoundException;
import ru.gb.spring.wintermarket.repositories.ProductRepository;
import ru.gb.spring.wintermarket.repositories.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    //find Page method!!!
    public Page<Product> getProducts(Integer minCost, Integer maxCost, String titlePart, Integer page){
        Specification<Product> spec = Specification.where(null);
        if(minCost != null){
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minCost));
        }
        if(maxCost != null){
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxCost));
        }
        if(titlePart != null){
            spec = spec.and(ProductSpecification.nameLike(titlePart));
        }
        return (productRepository.findAll(spec, PageRequest.of(page -1,5)))/*.stream().toList()*/;
    }
    //*****************************************

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    //Updates method
    @Transactional
    public Product update(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Невозможно найти данный объект id: "+ productDto.getId()));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }

}

