package shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Product;
import javax.sql.DataSource;

@Repository

public class ProductDAO {
    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products (name, price) VALUES (?,?);";
    private static final String SELECT_PRODUCTS_BY_ID = "select id,name,email,country from users where id =?;";
    private static final String SELECT_ALL_PRODUCTS = "select * from products;";
    private static final String DELETE_PRODUCTS_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,price= ? where id = ?;";

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ProductDAO() {}


    @Transactional(propagation = Propagation.NEVER,rollbackFor = SQLException.class)
    public void insertProduct(Product product)  {
        jdbcTemplate.update(INSERT_PRODUCTS_SQL,product.getName(),product.getPrice());
    }

    @Transactional(propagation = Propagation.NEVER,rollbackFor = SQLException.class)
    public Product selectProduct(int id) {
        return jdbcTemplate.query(SELECT_PRODUCTS_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    @Transactional(propagation = Propagation.NEVER,rollbackFor = SQLException.class,readOnly = true)
    public List < Product > selectAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS,new BeanPropertyRowMapper<>());
    }

    @Transactional(propagation = Propagation.NEVER,rollbackFor = SQLException.class)
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCTS_SQL, id);
    }

    @Transactional(propagation = Propagation.NEVER,rollbackFor = SQLException.class)
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCTS_SQL,product.getName(),product.getPrice(),product.getId());
    }


}
