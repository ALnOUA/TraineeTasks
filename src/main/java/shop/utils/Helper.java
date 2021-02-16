package shop.utils;

import shop.model.Product;
import shop.utils.menu.Menu;
import shop.utils.menu.MenuEntry;

import java.util.List;

public class Helper {
    public void showListToConsole(List<Product> products){
        System.out.println("===========================");
        System.out.println("List of products: ");
        int i=1;
        for(Product product: products){
            System.out.println(i+": "+product);
            i++;
        }
        System.out.println("===========================");
    }
    public void updateMenu(MenuEntry entry, Menu menu){
        menu.removeEntry(entry);
    }
}
