package shop.services;

import lombok.Data;
import shop.model.Product;
import java.util.List;
import static shop.utils.Resources.db_online_shop;
import static shop.utils.Resources.helper;

@Data
public class ProductService {

    public void addProductToBucket(Product product){
        if(product.getCurrency().getName().equalsIgnoreCase("uah")){
        product.setPrice((long) (product.getPrice()*product.getCurrency().getMultiplicity()));
        }
      //use strategy
        else {

        }
        db_online_shop.addProductToBucket(product);
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
