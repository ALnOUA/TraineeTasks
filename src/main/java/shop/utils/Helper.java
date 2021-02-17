package shop.utils;

import shop.model.Product;
import shop.utils.menu.Menu;
import shop.utils.menu.MenuEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helper {
    public void showListToConsole(List<Product> products){
        System.out.println("===========================");
        System.out.println("List of products: ");
        int i=1;
        for(Product product: products){
            System.out.println(i+": "+product.toString());
            i++;
        }
        System.out.println("===========================");
    }

    public void saveBucketList(List<Product> bucket){
        File file = new File("bucket.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream("bucket.txt")){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(bucket);
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> downloadBucketList() throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream("bucket.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Product> bucket = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
            return bucket;


        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new Exception("cant download bucket list");
    }
}
