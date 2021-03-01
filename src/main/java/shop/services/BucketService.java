package shop.services;

import shop.model.Product;
import shop.model.User;
import shop.utils.Helper;

import java.sql.SQLException;
import java.util.List;

import static shop.utils.Resources.db_online_shop;


public class BucketService {

    private Helper helper;
    public void saveBucket(Product product, User user) throws SQLException {
        db_online_shop.saveBucket(product,user);
    }

    public void clearBucket(){
        db_online_shop.deleteAllProductsFromBucket();
    }
    public void showAllProductsFromBucket() throws SQLException {
        helper.showListToConsole(db_online_shop.getAllProductsFromBucket());
    }
    public List<Product> getAllProductsFromBucket() throws SQLException {
        return db_online_shop.getAllProductsFromBucket();
    }
    public void deleteProductFromBucket(Product product){
        db_online_shop.deleteProductFromBucket(product);
    }
    public void deleteAllProductsFromBucket(){
        db_online_shop.deleteAllProductsFromBucket();
    }
    public int getSum(List<Product> bucket){
        return db_online_shop.getSum(bucket);
    }
}
