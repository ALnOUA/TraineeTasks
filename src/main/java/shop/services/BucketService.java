package shop.services;

import shop.model.Product;
import shop.utils.Helper;
import java.util.List;

import static shop.utils.Resources.db_online_shop;


public class BucketService {

    private Helper helper;

    public void clearBucket(){
        db_online_shop.deleteAllProductsFromBucket();
    }
    public void showAllProductsFromBucket(){
        helper.showListToConsole(db_online_shop.getAllProductsFromBucket());
    }
    public List<Product> getAllProductsFromBucket(){
        return db_online_shop.getAllProductsFromBucket();
    }
    public void deleteProductFromBucket(Product product){
        db_online_shop.deleteProductFromBucket(product);
    }
    public void deleteAllProductsFromBucket(){
        db_online_shop.deleteAllProductsFromBucket();
    }
}
