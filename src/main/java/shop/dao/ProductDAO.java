package shop.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.model.Food;
import shop.model.Product;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/online_shop?serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "leha21";

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products (name, price) VALUES (?,?);";

    private static final String SELECT_PRODUCTS_BY_ID = "select id,name,email,country from users where id =?;";
    private static final String SELECT_ALL_PRODUCTS = "select * from products;";
    private static final String DELETE_PRODUCTS_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,price= ? where id = ?;";

    public ProductDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCTS_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL);) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, String.valueOf(product.getPrice()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Product selectProduct(int id) {
       Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                long priceLong = Long.valueOf(price);
                product = new Product(name,priceLong) {
                };
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List < Product > selectAllProducts() {

        List < Product > products = new ArrayList < > ();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                long priceLong = Long.valueOf(price);
                products.add(new Product(id,name, priceLong) {
                });
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL)) {
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL)) {
            statement.setString(1, product.getName());
            statement.setString(2, String.valueOf(product.getPrice()));
            statement.setInt(4, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
