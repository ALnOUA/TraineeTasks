package shop.services;

import org.junit.Before;
import org.junit.Test;
import shop.model.Currency;
import shop.model.Food;
import shop.model.NotFood;
import shop.utils.db_imitation.DB_Online_Shop;

import static org.junit.Assert.*;

public class BucketServiceTest {

    BucketService bucketService = new BucketService();
    DB_Online_Shop db_online_shop = new DB_Online_Shop();
    @Before
    public void setUp() throws Exception {
        db_online_shop.addProductToBucket(new Food("Banana",new Currency(2,"USD",28,0.2),20,6));
        db_online_shop.addProductToBucket(new Food("Cherry",new Currency(2,"USD",28,0.2),20,6));
        db_online_shop.addProductToBucket(new NotFood("Car",new Currency(2,"USD",28,0.2),20));
    }

    @Test
    public void clearBucket() {
        bucketService.deleteAllProductsFromBucket();
        int sizeAfter = db_online_shop.getAllProductsFromBucket().size();
        assertEquals(0,sizeAfter);
    }

    @Test
    public void getAllProductsFromBucket() {
        int currentNumOfProducts = bucketService.getAllProductsFromBucket().size();
        assertEquals(3,currentNumOfProducts);
    }

    @Test
    public void deleteProductFromBucket() {
        bucketService.deleteProductFromBucket(bucketService.getAllProductsFromBucket().get(1));
        int currenNumOfProducts = bucketService.getAllProductsFromBucket().size();
        assertEquals(2,currenNumOfProducts);
    }
}