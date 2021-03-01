package shop.dao;
import lombok.Data;
import shop.model.*;
import shop.services.ProductService;
import shop.utils.annotations.SetLastUseDate;

import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DB_Online_Shop {
    public Connection connection = createConnection();
    private ProductService productService = new ProductService();
    private static List<Product> productList = new ArrayList();
    private static List<Product> bucket = new ArrayList<>();// TODO: 17.02.2021 add opportunity to have many user`s bucket.
    private static HashMap<String,ArrayList<Product>> warehouse = new HashMap<>();

    public Connection createConnection() {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/online_shop?serverTimezone=UTC";
            String username = "root";
            String password = "leha21";
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("======>Connected successfully<========");
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void initUser(User user) throws SQLException {
        PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT into users(username) VALUES ('"+user.getUserName()+"')");
        preparedStatement3.execute();
        preparedStatement3.close();
    }

    public  void initProducts() throws SQLException {


        if (productList.isEmpty()) {
            int i=1;
            productList.add(new Food(i++,"Banana",new Currency(2,"USD",28,1.2),20,20));
            productList.add(new Food(i++,"Pineapple",new Currency(2,"USD",28,0.2),25,2));
            productList.add(new Food(i++,"Apple",new Currency(2,"USD",28,1.2),30,5));
            productList.add(new Food(i++,"Cherry",new Currency(2,"USD",28,1.2),10,45));
           /* productList.add(new NotFood("Car",new Currency(2,"USD",28,1.2),1000));
            productList.add(new NotFood("Table",new Currency(2,"USD",28,1.2),200));
            productList.add(new NotFood("TV",new Currency(2,"USD",28,1.2),1500));*/




            // TODO: 17.02.2021 use warehouse somewhere
           /* addProductToWareHouse(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
            addProductToWareHouse(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
            addProductToWareHouse(new Food("Apple",new Currency(2,"USD",28,1.2),30,5));*/
        }

        for (int i=0;i<=productList.size()-1;i++){
        PreparedStatement preparedStatement = connection.prepareStatement("insert into products(name, price,expiration_days) VALUES ('"+productList.get(i).getName()+"','"+productList.get(i).getPrice()+"',null)");
        preparedStatement.execute();
        preparedStatement.close();
        }

        /*addProductToWareHouse(new Food("Banana",new Currency(2,"USD",28,1.2),20,20));
        addProductToWareHouse(new Food("Cherry",new Currency(2,"USD",28,1.2),20));
        System.out.println(warehouse.get("Cherry"));
        System.out.println(warehouse);*/

    }

    public void showUsersHistory(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("CALL SHOW_HISTORY('"+userId+"')");
        preparedStatement.execute();
        preparedStatement.close();
    }



    public void addProductToWareHouse(Product product){
        if(productService.hasExpireInfo(product)) {
            if (product instanceof Expirable && (((Food) product).getUseBeforeData().isBefore(LocalDateTime.now()))) {
                System.out.println(product + " is out of Date");
                // TODO: 17.02.2021 add logic when product is out of date

            }
        }
        else {
            Class<?> clazz = product.getClass();
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(SetLastUseDate.class)) {
                    SetLastUseDate annotation = m.getAnnotation(SetLastUseDate.class);
                    ((Food)product).setExpirationDays(annotation.setExpirationDays());
                }
            }
        }


        boolean elementFound=false;
        for (Map.Entry<String, ArrayList<Product>> entry : warehouse.entrySet()) {
            if (product.getName().equalsIgnoreCase(entry.getKey())){
                entry.getValue().add(product);
               elementFound=true;
                break;
            }

        }
        if (!elementFound){
            ArrayList<Product> bufList = new ArrayList<>();
            bufList.add(product);
            warehouse.put(product.getName(),bufList);

        }



    }
    public int getSum(List<Product> bucket){
        int sum=0 ;
        for (int i=0; i<=bucket.size(); i++){
            sum+= bucket.get(i).getPrice();
        }
        return sum;
    }

    public void saveBucket(Product product,User user) throws SQLException {
        LocalDateTime orderDate = LocalDateTime.now();
        System.out.println("||"+user.getId()+""+product.getId());
        PreparedStatement preparedStatement = connection.prepareStatement("insert into orders (user_id, product_id, order_date,order_result) values ((select user_id from users where username like '"+user.getUserName()+"'),'"+product.getId()+"','"+orderDate+"',true)");
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void addProductToBucket(Product product) throws SQLException {
        bucket.add(product);
    }
    public void deleteProductFromBucket(Product product){
        bucket.remove(product);
    }
    public void deleteAllProductsFromBucket(){
        ArrayList shouldBeRemoved = new ArrayList();
        for(Product product: bucket){
            shouldBeRemoved.add(product);
        }
        bucket.removeAll(shouldBeRemoved);


    }
    public List<Product> getAllProductsFromBucket() throws SQLException {
        List<Product> productListFromBucket = new ArrayList<>();
        for(Product product: bucket){
            productListFromBucket.add(product);
        }
        return productListFromBucket;
    }
    public List<Product> getAllFood(){
        List<Product> foodList = new ArrayList<>();
        for(Product product: productList){
            if(product instanceof Food){
                foodList.add(product);
            }
        }
        return foodList;
    }
    public List<Product> getAllNotFood(){
        List<Product> notFoodList = new ArrayList<>();
        for(Product product: productList){
            if(product instanceof NotFood){
                notFoodList.add(product);
            }
        }
        return notFoodList;
    }
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from products");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            long price = resultSet.getLong("price");
            productList.add(new Food(id,name,price));
        }
        return productList;
    }

    public void deleteProductById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete  from products where product_id like '"+id+"'");
        preparedStatement.execute();
        preparedStatement.close();
    }


    class ExpirationDaysSetter {

        public void process(Object instance, Food food) {
            Class<?> clazz = instance.getClass();
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(SetLastUseDate.class)) {
                    SetLastUseDate annotation = m.getAnnotation(SetLastUseDate.class);
                    food.setExpirationDays(annotation.setExpirationDays());
                }
            }
        }
    }
}
