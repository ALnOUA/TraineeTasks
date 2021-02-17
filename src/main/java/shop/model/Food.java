package shop.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Food extends Product implements Expirable  {
    private static long serialVersionUUID = 1095611956833581415L;
    private int expirationDate;
    private LocalDateTime useBeforeData;

    public Food(String name,Currency currency, long price,int expirationDate) {
        super(name,currency,price);
        this.expirationDate=expirationDate;
        this.useBeforeData=setLastUseDate();
    }

    @Override
    public String toString() {
        return "Food{" +
                "useBeforeData=" + useBeforeData +
                ", currency=" + currency +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public LocalDateTime setLastUseDate() {
        return LocalDateTime.now().plusDays(expirationDate);
    }
}
