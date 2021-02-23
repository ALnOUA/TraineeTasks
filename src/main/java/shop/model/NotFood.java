package shop.model;

import lombok.Data;

@Data
public class NotFood extends Product{
    public NotFood(int id,String name,Currency currency, long price) {
        super(id,name,currency,price);
    }

    @Override
    public String toString() {
        return "NotFood{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
