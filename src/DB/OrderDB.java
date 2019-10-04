package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Cart;
import BO.Item;
import BO.Billing;

public class OrderDB {

	public void placeorder(int uid) {
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet rs;
		int total_price = 0;

		try {
			connection = DBConnection.getConnection();

			// To get list of all cart
			CartDB cartDAO = new CartDB();
			List<Cart> result = new ArrayList<Cart>();
			result = cartDAO.selectAllItems(uid);

			// Total bill amount
			total_price = 2;

			// Insert into Billing table
			connection.setAutoCommit(false);

			String insertinbilling = "insert into billing (uid,total_amt) values(?,?)";

			preparedStatement = connection.prepareStatement(insertinbilling);

			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, total_price);

			preparedStatement.execute();
			preparedStatement.clearParameters();
			preparedStatement.close();

			// Get the latest bill

			String getBillNo = "select MAX(bill_no) from billing ";

			preparedStatement = connection.prepareStatement(getBillNo);

			rs = preparedStatement.executeQuery();

			rs.next();
			int bill_no = rs.getInt("MAX(bill_no)");

			preparedStatement.clearParameters();
			preparedStatement.close();

			// Insert into order_inv table

			String query = "insert into order_inv(bill_no,product_id,quantity,total_prod_price) values(?,?,?,?)";

			preparedStatement = connection.prepareStatement(query);

			for (Cart list : result) {
				preparedStatement.setInt(1, bill_no);
				preparedStatement.setInt(2, list.getProduct_id());
				preparedStatement.setInt(3, list.getQuantity());
				preparedStatement.setInt(4, list.getPrice());
				preparedStatement.execute();
				preparedStatement.clearParameters();
			}

			preparedStatement.close();

			// Delete entry from cart table

			String deletequery = "DELETE from cart where uid=? ";
			preparedStatement = connection.prepareStatement(deletequery);
			preparedStatement.setInt(1, uid);
			preparedStatement.execute();
			preparedStatement.clearParameters();
			preparedStatement.close();
			connection.commit();
			connection.setAutoCommit(true);

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
	public List<Billing> selectAllOrder() {
		Connection connection = null;
		PreparedStatement preparedStatement;
		List<Billing> billings = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM billing WHERE billing.packed = false");

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int bill_no = rs.getInt("bill_no");
				int total_amt = rs.getInt("total_amt");
				int uid = rs.getInt("uid");
				boolean packed = rs.getBoolean("packed");
				billings.add(new Billing(bill_no, uid, total_amt, packed));
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
		return billings;
	}
	
	
	public List<Billing> selectOrderByID(int order_id) {
		Connection connection = null;
		List<Billing> billing = new ArrayList<>();

		try {

			connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT product.product_name, order_inv.bill_no, order_inv.quantity, order_inv.product_id,  product.stock "
					+ "FROM order_inv "
					+ "INNER JOIN product "
					+ "ON order_inv.product_id = product.product_id "
					+ "WHERE order_inv.bill_no = ?");
			preparedStatement.setInt(1, order_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				int bill_no = rs.getInt("bill_no");
				int stock = rs.getInt("stock");
				billing.add(new Billing(bill_no, product_id, quantity, product_name, stock));
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
		return billing;
	}
	public boolean packed(int bill_no) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		boolean rowDeleted = false;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE billing SET billing.packed = true WHERE billing.bill_no = ?");
			preparedStatement.setInt(1, bill_no);
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

}