package shop.services;

import lombok.Data;
import shop.model.CurrencyPicker;
import shop.model.Expirable;
import shop.model.Food;
import shop.model.Product;

import java.sql.SQLException;
import java.util.List;
import static shop.utils.Resources.db_online_shop;
import static shop.utils.Resources.helper;

@Data
public class ProductService {
    public void addProductToBucket(Product product) throws RuntimeException, SQLException {
        if(product.getCurrency().getName().equalsIgnoreCase("uah")){
        product.setPrice((long) (product.getPrice()*product.getCurrency().getMultiplicity()));
        }
        else {
            CurrencyPicker currencyPicker = new CurrencyPicker();

            product.setPrice(currencyPicker.choosePrice(product.getCurrency().getName()).getPrice(product.getPrice()));
        }
        db_online_shop.addProductToBucket(product);
     }
     public boolean hasExpireInfo(Product product){
         if(product instanceof Expirable){
             if(((Food)product).getExpirationDays()!=0){
                 return true;
             }
             return false;
         }
         return false;
     }
     public void showAllProducts(){
        helper.showListToConsole(db_online_shop.getAllProducts());
     }
    public List<Product> getAllProducts(){
        return  db_online_shop.getAllProducts();
    }
    public  List<Product> getAllFoods(){
        return db_online_shop.getAllFood();
    }
    public  List<Product> getAllNotFoods(){
        return db_online_shop.getAllNotFood();
    }
    public void showAllProductsFromBucket(){
        helper.showListToConsole(db_online_shop.getAllProducts());
    }
     public void showFoods(){
        helper.showListToConsole(db_online_shop.getAllFood());
     }
     public void showNotFoods(){
        helper.showListToConsole(db_online_shop.getAllNotFood());
     }

}
