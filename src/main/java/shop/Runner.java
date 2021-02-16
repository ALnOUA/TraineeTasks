package shop;

import shop.model.Product;
import shop.services.BucketService;
import shop.services.ProductService;
import shop.utils.menu.Menu;
import shop.utils.menu.MenuEntry;

import java.util.List;

import static shop.utils.Resources.db_online_shop;

public class Runner {

    public static void main(String[] args) {
        db_online_shop.initProducts();
        ProductService productService = new ProductService();
        BucketService bucketService = new BucketService();
        MenuEntry clearBucket = new MenuEntry("Clear bucket") {
            Menu bucketMenu = new Menu();
            @Override
            public void run() {
                bucketService.deleteAllProductsFromBucket();
                System.out.println("Bucket was clear successfully");
            }
        };
        MenuEntry showBucket = new MenuEntry("Show bucket") {
            Menu bucketMenu = new Menu();
            @Override
            public void run() {
                System.out.println("Chose what product you want to delete from bucket");
                List<Product> allProductsFromBucket = bucketService.getAllProductsFromBucket();
                for (int i=0; i<=allProductsFromBucket.size()-1;i++){
                    int var = i;
                    bucketMenu.addEntry(new MenuEntry(allProductsFromBucket.get(i).getName()) {
                        @Override
                        public void run() {
                            System.out.println("Product "+allProductsFromBucket.get(var).getName()+" was successfully deleted from the bucket");
                            bucketService.deleteProductFromBucket(allProductsFromBucket.get(var));
                            allProductsFromBucket.remove(allProductsFromBucket.get(var));

                        }
                    });
                }

                bucketMenu.addEntry(clearBucket);
                bucketMenu.run();
            }
        };
        MenuEntry food = new MenuEntry("Food") {
            Menu foodMenu = new Menu();
            @Override
            public void run() {
                System.out.println("Chose what product you want to add to bucket:");
                List<Product> allFoods = productService.getAllFoods();
                for (int i=0; i<=allFoods.size()-1;i++){
                    int var = i;
                    foodMenu.addEntry(new MenuEntry(allFoods.get(i).getName()) {
                       @Override
                       public void run() {
                           productService.addProductToBucket(allFoods.get(var));
                           System.out.println("Product "+allFoods.get(var).getName()+" was successfully added");
                       }
                   });
                }foodMenu.run();


            }
        };
        MenuEntry notFood = new MenuEntry("NotFood") {
            Menu notFoodMenu = new Menu();
            @Override
            public void run() {
                System.out.println("Chose what product you want to add to bucket:");
                List<Product> allNotFoods = productService.getAllNotFoods();
                for (int i=0; i<=allNotFoods.size()-1;i++){
                    int var = i;
                    notFoodMenu.addEntry(new MenuEntry(allNotFoods.get(i).getName()) {
                        @Override
                        public void run() {
                            productService.addProductToBucket(allNotFoods.get(var));
                            System.out.println("Product "+allNotFoods.get(var).getName()+" was successfully added");
                        }
                    });
                }notFoodMenu.run();


            }
        };
        MenuEntry productList = new MenuEntry("Product menu") {
            Menu productMenu = new Menu();
            @Override
            public void run() {
                productMenu.addEntry(food);
                productMenu.addEntry(notFood);
                productMenu.run();

            }
        };
        MenuEntry bucketList = new MenuEntry("Bucket menu") {
            Menu bucketMenu = new Menu();
            @Override
            public void run() {
                bucketMenu.addEntry(showBucket);
                bucketMenu.run();

            }

        };


        Menu menu = new Menu();
        menu.addEntry(productList);
        menu.addEntry(bucketList);
        menu.run();
    }
}
