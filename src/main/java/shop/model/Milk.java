package shop.model;

import lombok.Data;

@Data
public class Milk extends Food {
    private String name;
    public Milk(int id,String name,Currency currency, long price,int expirationDate) {
        super(id,name,currency,price,expirationDate);
    }
}
