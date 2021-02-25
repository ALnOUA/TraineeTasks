package shop.model;

import lombok.Data;
import shop.utils.annotations.SetLastUseDate;

import java.time.LocalDateTime;

@Data
public class Food extends Product implements Expirable  {
    private static long serialVersionUUID = 1095611956833581415L;
    private int expirationDays=0;
    private LocalDateTime useBeforeData;

    public Food(int id, String name,Currency currency, long price,int expirationDays) {
        super(id,name,currency,price);
        this.expirationDays = expirationDays;
        this.useBeforeData=setLastUseDate();
    }
    public Food(int id,String name,Currency currency, long price) {
        super(id,name,currency,price);
        this.useBeforeData=setLastUseDate();
    }

    public Food(int id, String name, Currency currency, int price, int expirationDays) {
        super(id, name, currency, price);
        this.expirationDays = expirationDays;
        this.useBeforeData=setLastUseDate();
    }

    public Food(int id, String name, long price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "Food{" +
                "useBeforeData=" + useBeforeData +
                ", Expiration days " + expirationDays+
                ", currency=" + currency +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @SetLastUseDate(setExpirationDays = 45)
    public LocalDateTime setLastUseDate() {
        return LocalDateTime.now().plusDays(expirationDays);
    }
}
