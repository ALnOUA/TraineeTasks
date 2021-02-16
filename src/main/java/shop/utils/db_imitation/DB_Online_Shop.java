package shop.utils.db_imitation;

import lombok.Data;
import shop.model.Food;
import shop.model.NotFood;
import shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class DB_Online_Shop {
    private static List<Product> productList = new ArrayList();
    private static List<Product> bucket = new ArrayList<>();

    public  void initProducts(){
        if (productList.isEmpty()) {
            productList.add(new Food("Banana"));
            productList.add(new Food("Pineapple"));
            productList.add(new Food("Cherry"));
            productList.add(new NotFood("Table"));
            productList.add(new NotFood("Chair"));
            productList.add(new NotFood("Car"));
            productList.add(new NotFood("Door"));
        }
    }

    public void addProductToBucket(Product product){
        bucket.add(product);
    }
    public void deleteProductFromBucket(Product product){
        bucket.remove(product);
    }
    public void deleteAllProductsFromBucket(){
        ArrayList shouldBeRemoved = new ArrayList();
        for(Product product: bucket){
            shouldBeRemoved.add(product);
        }
        bucket.removeAll(shouldBeRemoved);


    }
    public List<Product> getAllProductsFromBucket(){
        List<Product> productListFromBucket = new ArrayList<>();
        for(Product product: bucket){
            productListFromBucket.add(product);
        }
        return productListFromBucket;
    }
    public List<Product> getAllFood(){
        List<Product> foodList = new ArrayList<>();
        for(Product product: productList){
            if(product instanceof Food){
                foodList.add(product);
            }
        }
        return foodList;
    }
    public List<Product> getAllNotFood(){
        List<Product> notFoodList = new ArrayList<>();
        for(Product product: productList){
            if(product instanceof NotFood){
                notFoodList.add(product);
            }
        }
        return notFoodList;
    }
    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        for(Product product: this.productList){
                productList.add(product);
        }
        return productList;
    }
}
