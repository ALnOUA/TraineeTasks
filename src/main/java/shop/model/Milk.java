package shop.model;

import lombok.Data;

@Data
public class Milk extends Food {
    private String name;
    public Milk(String name,Currency currency, long price,int expirationDate) {
        super(name,currency,price,expirationDate);
    }
}
