package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.Items;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class ItemDAO {
    private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/XdVvV2OhRA?useSSL=true";
    private String jdbcUsername = "XdVvV2OhRA";
    private String jdbcPassword = "qFzNXNgR0v";

    private static final String INSERT_ITEM_SQL = "INSERT INTO item (title, description, quantity, price) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ITEM_BY_ID = "select id,title,description,quantity,price from item where id =?";
    private static final String SELECT_ALL_ITEMS = "select * from item";
    private static final String DELETE_ITEMS_SQL = "DELETE FROM item where id = ?";
    private static final String UPDATE_ITEM_SQL = "UPDATE item SET title = ?, description = ?, quantity = ?, price = ? WHERE id = ?";

    public ItemDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getQuantity());
            preparedStatement.setString(4, item.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Item selectUser(int id) {
    	Item item = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String quantity = rs.getString("quantity");
                float price = rs.getString("price");
                item = new Item(id, title, description, quantity, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
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
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String quantity = rs.getString("quantity");
                float price = rs.getString("price");;
                items.add(new Item(id, title, description, quantity, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return items;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_ITEMS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(Item item) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_SQL);) {
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getQuantity());
            statement.setString(4, item.getPrice());
            statement.setInt(5, item.getId());

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