package com.ua;

import com.ua.dto.ProductDto;
import com.ua.model.Food;
import com.ua.model.NotFood;
import com.ua.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductConverter {
    public List<ProductDto> listProductDto(List<? extends Product> products){
       return products.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public ProductDto entityToDto(Object o){
        ProductDto productDto = null;
        if(o instanceof Food){
            productDto = fromFoodToDto((Food) o);
        }
        else if(o instanceof NotFood) {
            productDto = fromNotFoodToDto((NotFood) o);
        }
        return productDto;
    }

    private ProductDto fromFoodToDto(Food food){
        ProductDto productDto = new ProductDto();
        productDto.setName(food.getName());
        productDto.setExpirationDays(food.getExpirationDays());
        productDto.setProduct_id(food.getProduct_id());
        productDto.setCurrency(food.getCurrency());
        productDto.setPrice(food.getPrice());
        productDto.setUseBeforeData(food.getUseBeforeData());
        return productDto;
    }

    private ProductDto fromNotFoodToDto(NotFood notFood){
        ProductDto productDto = new ProductDto();
        productDto.setName(notFood.getName());
        productDto.setProduct_id(notFood.getProduct_id());
        productDto.setCurrency(notFood.getCurrency());
        productDto.setPrice(notFood.getPrice());
        productDto.setMaterial(notFood.getMaterial());
        return productDto;
    }
}
