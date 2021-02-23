package shop.services;

import org.junit.Before;
import org.junit.Test;
import shop.model.Currency;
import shop.model.Food;
import shop.utils.db_imitation.DB_Online_Shop;

import static org.junit.Assert.*;

public class ProductServiceTest {

    DB_Online_Shop db_online_shop = new DB_Online_Shop();
    BucketService bucketService = new BucketService();
    ProductService productService = new ProductService();
    @Before
    public void setUp() throws Exception {
        db_online_shop.initProducts();
    }

    @Test
    public void addProductToBucket() {
        db_online_shop.addProductToBucket(new Food("Banana",new Currency(2,"USD",28,0.2),20,2));
        db_online_shop.addProductToBucket(new Food("Apple",new Currency(2,"USD",28,0.2),20,4));
        int currentNumOfProducts = db_online_shop.getAllProductsFromBucket().size();
        assertEquals(2,currentNumOfProducts);
    }

    @Test
    public void getAllProducts() {
        int currenNumOfProducts = db_online_shop.getAllProducts().size();
        assertEquals(7,currenNumOfProducts);
    }

    @Test
    public void getAllFoods() {
        int currenNumOfFood = db_online_shop.getAllFood().size();
        assertEquals(3,currenNumOfFood);
    }

    @Test
    public void getAllNotFoods() {
        int currenNumOfNotFood = db_online_shop.getAllNotFood().size();
        assertEquals(4,currenNumOfNotFood);
    }
}