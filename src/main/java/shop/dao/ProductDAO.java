package shop.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Product;
import javax.sql.DataSource;

public class ProductDAO {
    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products (name, price) VALUES (?,?);";
    private static final String SELECT_PRODUCTS_BY_ID = "select id,name,email,country from users where id =?;";
    private static final String SELECT_ALL_PRODUCTS = "select * from products;";
    private static final String DELETE_PRODUCTS_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,price= ? where id = ?;";

    private DataSource dataSource;

    public ProductDAO() {}
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    @Transactional
    public void insertProduct(Product product)  {
        jdbcTemplate.update(INSERT_PRODUCTS_SQL,product.getName(),product.getPrice());
    }

    public Product selectProduct(int id) {
        return jdbcTemplate.query(SELECT_PRODUCTS_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    @Transactional
    public List < Product > selectAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS,new BeanPropertyRowMapper<>());
    }

    @Transactional
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCTS_SQL, id);
    }

    @Transactional
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCTS_SQL,product.getName(),product.getPrice(),product.getId());
    }


}
