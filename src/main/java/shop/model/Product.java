package shop.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Product implements Serializable {
    Currency currency;
    String name;
    long price;

    public Product(String name,Currency currency, long price) {
        this.name=name;
        this.currency= currency;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
