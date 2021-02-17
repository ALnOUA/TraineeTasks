package shop;

import shop.model.Food;
import shop.model.Product;
import shop.services.BucketService;
import shop.services.ProductService;
import shop.utils.Helper;
import shop.utils.Resources;
import shop.utils.menu.Menu;
import shop.utils.menu.MenuEntry;
import java.util.List;

import static shop.utils.Resources.db_online_shop;
import static shop.utils.Resources.helper;

public class Runner {


    public static void main(String[] args) throws Exception {
        Helper helper = new Helper();
        ProductService productService = new ProductService();
        BucketService bucketService = new BucketService();
        int sizeOfBucket = helper.downloadBucketList().size();
        if(sizeOfBucket!=0){
            for(Product product:helper.downloadBucketList()){
                productService.addProductToBucket(product);
            }
        }
        db_online_shop.initProducts();

        MenuEntry showBucket = getShowBucketEntry(bucketService);
        MenuEntry food = getFoodMenuEntry(productService, "Food", productService.getAllFoods());
        MenuEntry notFood = getNotFoodMenuEntry(productService, "NotFood", productService.getAllNotFoods());
        MenuEntry productList = getProductMenuEntry(food, notFood);
        MenuEntry bucketList = getBucketMenuEntry(showBucket);


        Menu menu = new Menu();
        menu.addEntry(productList);
        menu.addEntry(bucketList);
        menu.run();
        helper.saveBucketList(bucketService.getAllProductsFromBucket());
    }

    private static MenuEntry getShowBucketEntry(BucketService bucketService) {
        return new MenuEntry("Show bucket") {
                Menu bucketMenu = new Menu();
                @Override
                public void run() {
                    helper.showListToConsole(bucketService.getAllProductsFromBucket());
                    System.out.println("Chose what product you want to delete from bucket");
                    List<Product> allProductsFromBucket = bucketService.getAllProductsFromBucket();
                    for (int i=0; i<=allProductsFromBucket.size()-1;i++){
                        int var = i;
                        MenuEntry bucketEntry = new MenuEntry(allProductsFromBucket.get(i).getName()) {
                            @Override
                            public void run() {
                                bucketService.deleteProductFromBucket(allProductsFromBucket.get(var));
                                allProductsFromBucket.remove(allProductsFromBucket.get(var));
                                System.out.println("Product "+allProductsFromBucket.get(var).getName()+" was successfully deleted from the bucket");
                            }
                        };
                        bucketMenu.addEntry(bucketEntry);
                    }bucketMenu.run();


                }
            };
    }

    private static MenuEntry getNotFoodMenuEntry(ProductService productService, String notFood2, List<Product> allNotFoods2) {
        return new MenuEntry(notFood2) {
            Menu notFoodMenu = new Menu();

            @Override
            public void run() {
                System.out.println("Chose what product you want to add to bucket:");
                List<Product> allNotFoods = allNotFoods2;
                for (int i = 0; i <= allNotFoods.size() - 1; i++) {
                    int var = i;
                    notFoodMenu.addEntry(new MenuEntry(allNotFoods.get(i).getName()+" ["+allNotFoods.get(i).getPrice()+" "+allNotFoods.get(i).getCurrency().getName()+"] ") {
                        @Override
                        public void run() {
                            productService.addProductToBucket(allNotFoods.get(var));
                            System.out.println("Product " + allNotFoods.get(var).getName() + " was successfully added");
                        }
                    });
                }
                notFoodMenu.run();


            }
        };
    }
    private static MenuEntry getFoodMenuEntry(ProductService productService, String food, List<Product> allFoods) {
        return new MenuEntry(food) {
            Menu notFoodMenu = new Menu();

            @Override
            public void run() {
                System.out.println("Chose what product you want to add to bucket:");
                List<Product> localAllFoods = allFoods;
                for (int i = 0; i <= localAllFoods.size() - 1; i++) {
                    int var = i;
                    notFoodMenu.addEntry(new MenuEntry(localAllFoods.get(i).getName()+" ["+localAllFoods.get(i).getPrice()+" "+localAllFoods.get(i).getCurrency().getName()+"] ") {
                        @Override
                        public void run() {
                            productService.addProductToBucket(localAllFoods.get(var));
                            System.out.println("Product " + localAllFoods.get(var).getName() + " was successfully added");
                        }
                    });
                }
                notFoodMenu.run();


            }
        };
    }

    private static MenuEntry getProductMenuEntry(MenuEntry food, MenuEntry notFood) {
        return new MenuEntry("Product menu") {
                Menu productMenu = new Menu();
                @Override
                public void run() {
                    productMenu.addEntry(food);
                    productMenu.addEntry(notFood);
                    productMenu.run();

                }
            };
    }

    private static MenuEntry getBucketMenuEntry(MenuEntry showBucket) {
        return new MenuEntry("Bucket menu") {
                Menu bucketMenu = new Menu();
                @Override
                public void run() {
                    bucketMenu.addEntry(showBucket);
                    bucketMenu.run();

                }

            };
    }
}
