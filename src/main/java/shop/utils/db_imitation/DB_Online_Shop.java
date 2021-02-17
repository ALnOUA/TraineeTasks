package shop.utils.db_imitation;

import lombok.Data;
import shop.model.*;
import shop.services.BucketService;
import shop.services.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DB_Online_Shop {
    private static List<Product> productList = new ArrayList();
    private static List<Product> bucket = new ArrayList<>();// TODO: 17.02.2021 add opportunity to have many user`s bucket.
    private static HashMap<String,ArrayList<Product>> warehouse = new HashMap<>();

    public  void initProducts(){
        if (productList.isEmpty()) {
            productList.add(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
            productList.add(new Food("Pineapple",new Currency(2,"USD",28,0.2),25,2));
            productList.add(new Food("Apple",new Currency(2,"USD",28,1.2),30,5));
            productList.add(new Food("Cherry",new Currency(2,"USD",28,1.2),10,45));
            productList.add(new NotFood("Car",new Currency(2,"USD",28,1.2),1000));
            productList.add(new NotFood("Table",new Currency(2,"USD",28,1.2),200));
            productList.add(new NotFood("TV",new Currency(2,"USD",28,1.2),1500));

            // TODO: 17.02.2021 use warehouse somewhere
            /*addProductToWareHouse(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
            addProductToWareHouse(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
            addProductToWareHouse(new Food("Apple",new Currency(2,"USD",28,1.2),30,5));*/
        }

        System.out.println(warehouse);

    }

    public void addProductToWareHouse(Product product){
        if(product instanceof Expirable && (((Food) product).getUseBeforeData().isBefore(LocalDateTime.now()))){
            System.out.println(product+" is out of Date");
            // TODO: 17.02.2021 add logic when product is out of date

        }


        boolean elementFound=false;
        for (Map.Entry<String, ArrayList<Product>> entry : warehouse.entrySet()) {
            if (product.getName().equalsIgnoreCase(entry.getKey())){
                entry.getValue().add(product);
               elementFound=true;
                break;
            }

        }
        if (!elementFound){
            ArrayList<Product> bufList = new ArrayList<>();
            bufList.add(product);
            warehouse.put(product.getName(),bufList);

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
