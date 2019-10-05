package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Item;

public class ItemDB {
	private static final String INSERT_ITEM_SQL = "INSERT INTO product (cat_id, product_name, price, stock) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ITEM_BY_ID = "select product_id,product_name,cat_id,stock,price from product where product_id =?";
	private static final String SELECT_ALL_ITEMS = "select * from product";
	private static final String SELECT_ALL_ITEMS_BY_CAT = "select * from product where cat_id = ?";
	private static final String DELETE_ITEMS_SQL = "DELETE FROM product where product_id = ?";
	private static final String UPDATE_ITEM_SQL = "UPDATE product SET product_name = ?, cat_id = ?, stock = ?, price = ? WHERE product_id = ?";

	public ItemDB() {
	}

	public void insertItem(Item item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;

		System.out.println(INSERT_ITEM_SQL);

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL);
			preparedStatement.setInt(1, item.getCat_id());
			preparedStatement.setString(2, item.getProduct_name());
			preparedStatement.setFloat(3, item.getPrice());
			preparedStatement.setInt(4, item.getStock());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public Item selectItem(int product_id) {
		Connection connection = null;
		PreparedStatement preparedStatement;
		Item item = null;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);
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
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		System.out.println(item);
		return item;
	}

	public List<Item> selectAllItems() {
		Connection connection = null;
		PreparedStatement preparedStatement;
		List<Item> items = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);
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
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return items;
	}

	public List<Item> selectAllItemsByCat(int cat_id) {
		Connection connection = null;
		PreparedStatement preparedStatement;
		List<Item> items = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BY_CAT);
			preparedStatement.setInt(1, cat_id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int cat_id2 = rs.getInt("cat_id");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				items.add(new Item(product_id, product_name, cat_id2, stock, price));
			}
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return items;
	}

	public boolean deleteItem(int product_id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		boolean rowDeleted = false;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_ITEMS_SQL);
			preparedStatement.setInt(1, product_id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return rowDeleted;
	}

	public boolean updateItem(Item item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		boolean rowUpdated = false;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL);
			preparedStatement.setString(1, item.getProduct_name());
			preparedStatement.setInt(2, item.getCat_id());
			preparedStatement.setInt(3, item.getStock());
			preparedStatement.setFloat(4, item.getPrice());
			preparedStatement.setInt(5, item.getProduct_id());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return rowUpdated;
	}

}