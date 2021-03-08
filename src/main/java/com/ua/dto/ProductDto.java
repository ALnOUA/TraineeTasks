package com.ua.dto;

import com.ua.model.Currency;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long product_id;
    private Currency currency;
    private String name;
    private float price;
    private int expirationDays;
    private String useBeforeData;
    private String material;
}
