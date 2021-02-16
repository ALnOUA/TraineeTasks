package shop.utils;

import lombok.Data;
import shop.utils.db_imitation.DB_Online_Shop;

@Data
public class Resources {
    public static DB_Online_Shop db_online_shop = new DB_Online_Shop();
    public static Helper helper = new Helper();


}
