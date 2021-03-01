package shop.model;

import lombok.Data;

import java.io.Serializable;

@Data

public abstract class Product implements Serializable {
    int id;
    Currency currency;
    String name;
    long price;

    public Product(int id,String name,Currency currency, long price) {
        this.id=id;
        this.name=name;
        this.currency= currency;
        this.price = price;
    }

    public Product(int id, String name, long price) {

    }

    public Product(String name, long price) {
        this.name = name;
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
