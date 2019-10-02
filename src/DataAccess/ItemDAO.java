package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.Item;


public class ItemDAO {
    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/XdVvV2OhRA?useSSL=true";
    private String jdbcUsername = "XdVvV2OhRA";
    private String jdbcPassword = "qFzNXNgR0v";

    private static final String INSERT_ITEM_SQL = "INSERT INTO product (cat_id, product_name, price, stock) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ITEM_BY_ID = "select product_id,product_name,cat_id,stock,price from product where product_id =?";
    private static final String SELECT_ALL_ITEMS = "select * from product";
    private static final String DELETE_ITEMS_SQL = "DELETE FROM product where product_id = ?";
    private static final String UPDATE_ITEM_SQL = "UPDATE product SET product_name = ?, cat_id = ?, stock = ?, price = ? WHERE product_id = ?";

    public ItemDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertItem(Item item) throws SQLException {
        System.out.println(INSERT_ITEM_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setInt(1, item.getCat_id());
            preparedStatement.setString(2, item.getProduct_name());
            preparedStatement.setFloat(3, item.getPrice());
            preparedStatement.setInt(4, item.getStock());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Item selectItem(int product_id) {
    	Item item = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);) {
            preparedStatement.setInt(1, product_id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String product_name = rs.getString("product_name");
                int cat_id = rs.getInt("cat_id");
                int stock = rs.getInt("stock");
                int price = rs.getInt("price");
                item = new Item(product_id, product_name, cat_id, stock, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println(item);
        return item;
    }

    public List < Item > selectAllItems() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Item > items = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int cat_id = rs.getInt("cat_id");
                int stock = rs.getInt("stock");
                int price = rs.getInt("price");
                items.add(new Item(product_id, product_name, cat_id, stock, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }

    public boolean deleteItem(int product_id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_ITEMS_SQL);) {
            statement.setInt(1, product_id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateItem(Item item) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_SQL);) {
            statement.setString(1, item.getProduct_name());
            statement.setInt(2, item.getCat_id());
            statement.setInt(3, item.getStock());
            statement.setFloat(4, item.getPrice());
            statement.setInt(5, item.getProduct_id());

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