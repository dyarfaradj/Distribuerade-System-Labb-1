package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Cart;

public class CartDB {

	private static final String SELECT_ALL_ITEMS = "select cart.product_id,product_name,quantity,cart.price from cart inner join product on cart.product_id = product.product_id where uid=?";
	private static final String UPDATE_STOCK = "select product_id,price, stock from product where product_id= ?";
	private static final String SELECT_ITEMS_USER_CART = "select cart.product_id,product_name,quantity,cart.price from cart inner join product on cart.product_id = product.product_id where uid=?";
	private static final String GET_TOTAL = "select price from cart where uid= ? ";

	public static void insertIntoCart(int id, int pid, int quantity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs;

		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(UPDATE_STOCK, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setInt(1, pid);
			rs = preparedStatement.executeQuery();
			rs.next();
			int price = rs.getInt("price");
			int new_stock = rs.getInt("stock");

			new_stock = new_stock - quantity;

			rs.updateInt("stock", new_stock);

			rs.updateRow();

			preparedStatement.clearParameters();

			preparedStatement.close();
			System.out.println(quantity);

			String sql = "INSERT INTO cart(uid,product_id,quantity,price)VALUES(?,?,?,?)";
			String sql1 = "UPDATE `cart` SET `quantity`=?,`price`=? WHERE `uid`= ? AND `product_id`= ?";

			preparedStatement = connection.prepareStatement(sql);

			PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_ALL_ITEMS);
			preparedStatement2.setInt(1, id);

			ResultSet rs1 = preparedStatement2.executeQuery();

			boolean check = false;
			int product_id;
			while (rs1.next()) {
				product_id = rs1.getInt("product_id");
				if (product_id == pid) {
					pid = product_id;
					quantity += 1;
					check = true;
				}
			}
			int total_price = price * quantity;

			if (check == false) {
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, pid);
				preparedStatement.setInt(3, quantity);
				preparedStatement.setInt(4, total_price);

				preparedStatement.execute();

				preparedStatement.clearParameters();
				preparedStatement.close();
			} else {

				PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
				System.out.println(preparedStatement1);

				preparedStatement1.setInt(1, quantity);
				preparedStatement1.setInt(2, total_price);
				preparedStatement1.setInt(3, id);
				preparedStatement1.setInt(4, pid);
				preparedStatement1.executeUpdate();

				System.out.println(preparedStatement1);

			}

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				DBConnection.printSQLException(e);
			}
		}

	}

	public List<Cart> selectAllItems(int uid) {
		Connection connection = null;
		List<Cart> cart = new ArrayList<>();

		try {

			connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);
			preparedStatement.setInt(1, uid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				cart.add(new Cart(product_id, product_name, quantity, price));
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
		return cart;
	}

	public static int getStock(int productId) {

		int stockId = 0;
		Connection connection = DBConnection.getConnection();

		String sql = "select stock from product where product_id= ?";

		try {

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				stockId = rs.getInt("stock");
			}

		} catch (SQLException e) {

			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				DBConnection.printSQLException(e);
			}
		}

		return stockId;

	}

	public static List<Cart> getProducts(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Cart> carts = new ArrayList<Cart>();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ITEMS_USER_CART);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Cart cart = new Cart();

				cart.setProduct_id(rs.getInt("product_id"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setPrice(rs.getInt("price"));

				carts.add(cart);
			}
		} catch (SQLException e) {

			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				DBConnection.printSQLException(e);
			}
		}
		return carts;
	}

	public static int getTotal(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int totalprice = 0;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_TOTAL);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				totalprice = totalprice + rs.getInt("price");
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

		return totalprice;
	}
}