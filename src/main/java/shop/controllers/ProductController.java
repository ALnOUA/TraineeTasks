package shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.dao.DB_Online_Shop;
import shop.dao.ProductDAO;
import shop.model.Product;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {
    private ProductDAO productDAO = new ProductDAO();
    @GetMapping("list")
    public ModelAndView getListProduct() {
        ModelAndView mv = new ModelAndView();
        List <Product> listProduct = productDAO.selectAllProducts();
        mv.addObject("listProduct",listProduct);
        mv.setViewName("product-list.jsp");
        return mv;
    }
    @PostMapping("insert")
    public ModelAndView insertProduct(@RequestParam("name") String name, @RequestParam("price") String price) throws SQLException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product-form.jsp");
        long priceLong = Long.valueOf(price);
        Product newProduct = new Product(name, priceLong) {
        };
        productDAO.insertProduct(newProduct);

        modelAndView.setViewName("product-list.jsp");
        return modelAndView;
    }
    @PostMapping("update")
    public ModelAndView updateProduct(@RequestParam("id") int id,
                                      @RequestParam("name") String name,
                                      @RequestParam("price") String price){
        ModelAndView modelAndView = new ModelAndView();
        long priceLong = Long.valueOf(price);
        Product product = new Product(id, name, priceLong) {
        };
        productDAO.updateProduct(product);
        modelAndView.setViewName("product-list.jsp");
        return modelAndView;
    }
    @DeleteMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        productDAO.deleteProduct(id);
        modelAndView.setViewName("product-list.jsp");
        return modelAndView;
    }

}